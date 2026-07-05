package com.micronews.media.domain;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MediaFacade {
    final ImageRepository imageRepository;

    public MediaFacade(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Optional<String> findLeadImageHrefByArticleId(Integer articleId) {
        return imageRepository.findByArticleIdsContaining(articleId)
                .map(image -> image.href);
    }
}
