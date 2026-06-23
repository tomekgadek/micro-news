package com.micronews.activity.domain;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ActivityFacade {
    private final ArticleUserRepository articleUserRepository;

    public ActivityFacade(ArticleUserRepository articleUserRepository) {
        this.articleUserRepository = articleUserRepository;
    }
}
