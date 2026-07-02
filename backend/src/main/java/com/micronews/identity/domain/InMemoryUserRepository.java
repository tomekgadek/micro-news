package com.micronews.identity.domain;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.requireNonNull;

class InMemoryUserRepository implements UserRepository {
    private final ConcurrentHashMap<Integer, User> map = new ConcurrentHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(1);

    @Override
    public User save(User user) {
        requireNonNull(user);
        if (user.id == null) {
            user = new User(seq.getAndIncrement(), user.firstname, user.surname, user.city);
        }
        map.put(user.id, user);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return map.get(id);
    }
}
