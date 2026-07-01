package com.micronews.identity.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

interface LoginRepository extends Repository<Login, String> {
    Login save(Login login);
    Optional<Login> findByLogin(String login);
}
