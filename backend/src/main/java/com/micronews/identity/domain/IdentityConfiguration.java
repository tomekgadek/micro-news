package com.micronews.identity.domain;

import com.micronews.infrastructure.config.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
class IdentityConfiguration {

    IdentityFacade identityFacade() {
        return new IdentityFacade(
                new InMemoryUserRepository(),
                new InMemoryLoginRepository(),
                new BCryptPasswordEncoder(),
                new JwtService()
        );
    }

    @Bean
    IdentityFacade identityFacade(
            UserRepository userRepository,
            LoginRepository loginRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        return new IdentityFacade(userRepository, loginRepository, passwordEncoder, jwtService);
    }
}
