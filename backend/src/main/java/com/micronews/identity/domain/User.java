package com.micronews.identity.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String surname;
    private String city;

    User() {}
}
