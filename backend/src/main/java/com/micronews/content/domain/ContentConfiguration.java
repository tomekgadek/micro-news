package com.micronews.content.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ContentConfiguration {

    @Bean
    ContentFacade contentFacade(ArticleRepository articleRepository, SectionRepository sectionRepository) {
        return new ContentFacade(articleRepository, sectionRepository);
    }
}
