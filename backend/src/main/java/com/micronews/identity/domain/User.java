package com.micronews.identity.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String firstname;
    String surname;
    String city;

    User() {}

    User(Integer id, String firstname, String surname, String city) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.city = city;
    }
}
