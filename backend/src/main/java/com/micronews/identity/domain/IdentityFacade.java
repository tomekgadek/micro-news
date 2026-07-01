package com.micronews.identity.domain;

import com.micronews.identity.dto.UserCredentialsDto;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class IdentityFacade {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    public IdentityFacade(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    public Optional<UserCredentialsDto> findCredentialsByLogin(String login) {
        return loginRepository.findByLogin(login)
                .map(l -> new UserCredentialsDto(l.getLogin(), l.getPass(), l.getRole()));
    }
}
