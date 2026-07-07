package com.micronews.identity.domain;

import com.micronews.identity.dto.UserNotFoundException;
import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<User, Integer> {
    User save(User user);
    User findById(Integer id);

    default User findOneOrThrow(Integer id) {
        User user = findById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }
}
