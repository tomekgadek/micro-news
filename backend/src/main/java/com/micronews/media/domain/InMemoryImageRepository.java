package com.micronews.media.domain;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.requireNonNull;

class InMemoryImageRepository implements ImageRepository {
    private final ConcurrentHashMap<Integer, Image> map = new ConcurrentHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(1);

    @Override
    public Image save(Image image) {
        requireNonNull(image);
        if (image.id == null) {
            image = new Image(seq.getAndIncrement(), image.href, image.title, image.articleIds);
        }
        map.put(image.id, image);
        return image;
    }

    @Override
    public Image findById(Integer id) {
        return map.get(id);
    }

    @Override
    public Optional<Image> findByArticleIdsContaining(Integer articleId) {
        return map.values().stream()
                .filter(img -> img.articleIds.contains(articleId))
                .findFirst();
    }
}
