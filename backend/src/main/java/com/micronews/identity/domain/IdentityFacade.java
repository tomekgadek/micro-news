package com.micronews.identity.domain;

import com.micronews.identity.dto.UserCredentialsDto;
import com.micronews.identity.dto.LoginRequest;
import com.micronews.identity.dto.LoginResponse;
import com.micronews.identity.dto.InvalidCredentialsException;
import com.micronews.infrastructure.config.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class IdentityFacade {
    final UserRepository userRepository;
    final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public IdentityFacade(UserRepository userRepository, LoginRepository loginRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Optional<UserCredentialsDto> findCredentialsByLogin(String login) {
        return loginRepository.findByLogin(login)
                .map(l -> new UserCredentialsDto(l.getLogin(), l.getPass(), l.getRole()));
    }

    public LoginResponse login(LoginRequest loginRequest) {
        UserCredentialsDto credentials = findCredentialsByLogin(loginRequest.login())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(loginRequest.password(), credentials.passwordHash())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(credentials.login(), credentials.role());
        return new LoginResponse(token);
    }
}
