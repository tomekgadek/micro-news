package com.micronews.content.domain;

import com.micronews.content.dto.ArticleNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

interface ArticleRepository extends Repository<Article, Integer> {
    Article save(Article article);
    Article findById(Integer id);
    Page<Article> findAll(Pageable pageable);
    
    @Query("SELECT a FROM Article a WHERE a.dateArt = " +
           "(SELECT MAX(a2.dateArt) FROM Article a2 WHERE a2.idSection = a.idSection) " +
           "ORDER BY a.dateArt DESC")
    List<Article> findLatestArticlesPerCategory(Pageable pageable);

    default Article findOneOrThrow(Integer id) {
        Article article = findById(id);
        if (article == null) {
            throw new ArticleNotFoundException(id);
        }
        return article;
    }
}
