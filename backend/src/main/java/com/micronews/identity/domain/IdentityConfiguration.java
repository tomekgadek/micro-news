package com.micronews.identity.domain;

import com.micronews.infrastructure.config.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.validation.Validator;
import jakarta.validation.Validation;
import java.util.List;

@Configuration
class IdentityConfiguration {

    IdentityFacade identityFacade() {
        return new IdentityFacade(
                new InMemoryUserRepository(),
                new InMemoryLoginRepository(),
                new BCryptPasswordEncoder(),
                new JwtService(),
                Validation.buildDefaultValidatorFactory().getValidator()
        );
    }

    @Bean
    IdentityFacade identityFacade(
            UserRepository userRepository,
            LoginRepository loginRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            Validator validator,
            @Value("${app.admin-ids:}") List<Integer> adminIds
    ) {
        return new IdentityFacade(userRepository, loginRepository, passwordEncoder, jwtService, validator, adminIds);
    }
}
