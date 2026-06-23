package com.micronews.identity.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class IdentityConfiguration {

    @Bean
    IdentityFacade identityFacade(UserRepository userRepository, LoginRepository loginRepository) {
        return new IdentityFacade(userRepository, loginRepository);
    }
}
