package com.micronews.content.domain;

import org.springframework.data.repository.Repository;

interface SectionRepository extends Repository<Section, Integer> {
    Section save(Section section);
    Section findById(Integer id);
}
