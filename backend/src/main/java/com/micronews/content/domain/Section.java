package com.micronews.content.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "section")
class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    Section() {}
}
