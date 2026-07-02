package com.micronews.identity.domain;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

class InMemoryLoginRepository implements LoginRepository {
    private final ConcurrentHashMap<String, Login> map = new ConcurrentHashMap<>();

    @Override
    public Login save(Login login) {
        requireNonNull(login);
        map.put(login.getLogin(), login);
        return login;
    }

    @Override
    public Optional<Login> findByLogin(String login) {
        return Optional.ofNullable(map.get(login));
    }
}
