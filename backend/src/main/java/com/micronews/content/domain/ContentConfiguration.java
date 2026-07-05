package com.micronews.content.domain;

import com.micronews.media.domain.MediaFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ContentConfiguration {

    ContentFacade contentFacade(MediaFacade mediaFacade) {
        return new ContentFacade(new InMemoryArticleRepository(), new InMemorySectionRepository(), mediaFacade);
    }

    @Bean
    ContentFacade contentFacade(ArticleRepository articleRepository, SectionRepository sectionRepository, MediaFacade mediaFacade) {
        return new ContentFacade(articleRepository, sectionRepository, mediaFacade);
    }
}
