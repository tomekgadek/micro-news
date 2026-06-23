package com.micronews.identity.domain;

import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<User, Integer> {
    User save(User user);
    User findById(Integer id);
}
