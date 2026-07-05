package com.micronews.content.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

interface ArticleRepository extends Repository<Article, Integer> {
    Article save(Article article);
    Article findById(Integer id);
    
    @Query("SELECT a FROM Article a WHERE a.dateArt = " +
           "(SELECT MAX(a2.dateArt) FROM Article a2 WHERE a2.idSection = a.idSection) " +
           "ORDER BY a.dateArt DESC")
    List<Article> findLatestArticlesPerCategory(Pageable pageable);
}
