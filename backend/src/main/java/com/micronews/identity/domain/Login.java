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

    private String role;

    Login() {}

    String getLogin() {
        return login;
    }

    String getPass() {
        return pass;
    }

    String getRole() {
        return role;
    }
}
