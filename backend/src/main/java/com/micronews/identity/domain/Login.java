package com.micronews.identity.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "login")
class Login {
    @Id
    @Column(name = "login", length = 100)
    private String login;
    
    private String pass;
    
    @Column(name = "id_user", unique = true)
    private Integer idUser;

    Login() {}
}
