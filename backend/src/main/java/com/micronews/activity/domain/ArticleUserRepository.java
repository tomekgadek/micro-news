package com.micronews.activity.domain;

import org.springframework.data.repository.Repository;

interface ArticleUserRepository extends Repository<ArticleUser, ArticleUserId> {
    ArticleUser save(ArticleUser articleUser);
}
