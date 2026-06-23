package com.micronews.media.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MediaConfiguration {

    @Bean
    MediaFacade mediaFacade(ImageRepository imageRepository) {
        return new MediaFacade(imageRepository);
    }
}
