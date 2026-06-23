package com.micronews.activity.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ActivityConfiguration {

    @Bean
    ActivityFacade activityFacade(ArticleUserRepository articleUserRepository) {
        return new ActivityFacade(articleUserRepository);
    }
}
