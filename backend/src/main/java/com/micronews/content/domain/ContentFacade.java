package com.micronews.content.domain;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ContentFacade {
    private final ArticleRepository articleRepository;
    private final SectionRepository sectionRepository;

    public ContentFacade(ArticleRepository articleRepository, SectionRepository sectionRepository) {
        this.articleRepository = articleRepository;
        this.sectionRepository = sectionRepository;
    }
}
