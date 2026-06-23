package com.micronews.identity.domain;

import org.springframework.data.repository.Repository;

interface LoginRepository extends Repository<Login, String> {
    Login save(Login login);
    Login findByLogin(String login);
}
