package com.micronews.media.domain;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MediaFacade {
    private final ImageRepository imageRepository;

    public MediaFacade(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
}
