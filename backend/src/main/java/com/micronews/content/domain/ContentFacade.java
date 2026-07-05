package com.micronews.content.domain;

import com.micronews.content.dto.PublicArticleDto;
import com.micronews.media.domain.MediaFacade;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public class ContentFacade {
    final ArticleRepository articleRepository;
    final SectionRepository sectionRepository;
    private final MediaFacade mediaFacade;

    public ContentFacade(ArticleRepository articleRepository, SectionRepository sectionRepository, MediaFacade mediaFacade) {
        this.articleRepository = articleRepository;
        this.sectionRepository = sectionRepository;
        this.mediaFacade = mediaFacade;
    }

    public List<PublicArticleDto> getRecentPublicArticles() {
        List<Article> selectedArticles = articleRepository.findLatestArticlesPerCategory(
                org.springframework.data.domain.PageRequest.of(0, 3)
        );

        return selectedArticles.stream()
                .map(article -> {
                    Section section = sectionRepository.findById(article.idSection);
                    String categoryName = (section != null) ? section.name : "Unknown";
                    String leadImage = mediaFacade.findLeadImageHrefByArticleId(article.id).orElse(null);
                    return new PublicArticleDto(
                            categoryName,
                            article.title,
                            leadImage,
                            article.content,
                            article.dateArt
                    );
                })
                .collect(Collectors.toList());
    }
}
