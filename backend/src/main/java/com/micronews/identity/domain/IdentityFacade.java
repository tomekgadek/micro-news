package com.micronews.identity.domain;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class IdentityFacade {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    public IdentityFacade(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }
}
