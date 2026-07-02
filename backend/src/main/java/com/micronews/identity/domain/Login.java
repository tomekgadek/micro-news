package com.micronews.identity.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "login")
class Login {
    @Id
    @Column(name = "login", length = 100)
    String login;
    
    String pass;
    
    @Column(name = "id_user", unique = true)
    Integer idUser;

    String role;

    Login() {}

    Login(String login, String pass, Integer idUser, String role) {
        this.login = login;
        this.pass = pass;
        this.idUser = idUser;
        this.role = role;
    }

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
