package com.micronews.content.domain;

import com.micronews.content.dto.SectionNotFoundException;
import org.springframework.data.repository.Repository;

import java.util.List;

interface SectionRepository extends Repository<Section, Integer> {
    Section save(Section section);
    Section findById(Integer id);
    List<Section> findAll();

    default Section findOneOrThrow(Integer id) {
        Section section = findById(id);
        if (section == null) {
            throw new SectionNotFoundException(id);
        }
        return section;
    }
}
