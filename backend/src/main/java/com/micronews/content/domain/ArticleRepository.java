package com.micronews.content.domain;

import org.springframework.data.repository.Repository;

interface ArticleRepository extends Repository<Article, Integer> {
    Article save(Article article);
    Article findById(Integer id);
}
