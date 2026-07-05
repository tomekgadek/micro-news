package com.micronews.content.domain;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.requireNonNull;

class InMemorySectionRepository implements SectionRepository {
    private final ConcurrentHashMap<Integer, Section> map = new ConcurrentHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(1);

    @Override
    public Section save(Section section) {
        requireNonNull(section);
        if (section.id == null) {
            section = new Section(seq.getAndIncrement(), section.name);
        }
        map.put(section.id, section);
        return section;
    }

    @Override
    public Section findById(Integer id) {
        return map.get(id);
    }
}
