package com.micronews.content.domain;

import com.micronews.content.dto.SectionDto;
import jakarta.persistence.*;

@Entity
@Table(name = "section")
class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    Section() {}

    Section(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    SectionDto dto() {
        return new SectionDto(id, name);
    }
}
