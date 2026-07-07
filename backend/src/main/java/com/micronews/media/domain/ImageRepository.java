package com.micronews.media.domain;

import com.micronews.media.dto.ImageNotFoundException;
import java.util.Optional;
import org.springframework.data.repository.Repository;

interface ImageRepository extends Repository<Image, Integer> {
    Image save(Image image);
    Image findById(Integer id);
    Optional<Image> findByArticleIdsContaining(Integer articleId);

    default Image findOneOrThrow(Integer id) {
        Image image = findById(id);
        if (image == null) {
            throw new ImageNotFoundException(id);
        }
        return image;
    }
}
