package com.micronews.media.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

interface ImageRepository extends Repository<Image, Integer> {
    Image save(Image image);
    Image findById(Integer id);
    Optional<Image> findByArticleIdsContaining(Integer articleId);
}
