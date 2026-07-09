package com.micronews.content.domain;

import com.micronews.content.dto.ArticleDetailsDto;
import com.micronews.content.dto.ArticleDto;
import com.micronews.content.dto.ArticleSummaryDto;
import com.micronews.content.dto.ArticleNotFoundException;
import com.micronews.content.dto.PublicArticleDto;
import com.micronews.content.dto.SectionDto;
import com.micronews.media.domain.MediaFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                    ArticleDto articleDto = article.dto();
                    Section section = sectionRepository.findOneOrThrow(articleDto.idSection());
                    String categoryName = section.dto().name();
                    String leadImage = mediaFacade.findLeadImageHrefByArticleId(articleDto.id()).orElse(null);
                    return new PublicArticleDto(
                            articleDto.id(),
                            categoryName,
                            articleDto.title(),
                            leadImage,
                            articleDto.content(),
                            articleDto.dateArt()
                    );
                })
                .collect(Collectors.toList());
    }

    public Page<ArticleSummaryDto> getArticles(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(article -> {
            ArticleDto articleDto = article.dto();
            Section section = sectionRepository.findOneOrThrow(articleDto.idSection());
            String categoryName = section.dto().name();
            String leadImage = mediaFacade.findLeadImageHrefByArticleId(articleDto.id()).orElse(null);
            return new ArticleSummaryDto(
                    articleDto.id(),
                    categoryName,
                    articleDto.title(),
                    leadImage,
                    articleDto.dateArt()
            );
        });
    }

    public ArticleDetailsDto getArticleDetails(Integer id) {
        ArticleDto articleDto = articleRepository.findOneOrThrow(id).dto();
        Section section = sectionRepository.findOneOrThrow(articleDto.idSection());
        String categoryName = section.dto().name();
        String leadImage = mediaFacade.findLeadImageHrefByArticleId(articleDto.id()).orElse(null);
        return new ArticleDetailsDto(
                articleDto.id(),
                categoryName,
                articleDto.title(),
                leadImage,
                articleDto.content(),
                articleDto.dateArt()
        );
    }

    public List<SectionDto> getSections() {
        return sectionRepository.findAll().stream()
                .map(Section::dto)
                .collect(Collectors.toList());
    }
}
