package com.micronews.media.domain;

import org.springframework.data.repository.Repository;

interface ImageRepository extends Repository<Image, Integer> {
    Image save(Image image);
    Image findById(Integer id);
}
