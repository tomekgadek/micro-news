package com.micronews.identity.domain;

import com.micronews.identity.dto.UserCredentialsDto;
import com.micronews.identity.dto.LoginRequest;
import com.micronews.identity.dto.LoginResponse;
import com.micronews.identity.dto.InvalidCredentialsException;
import com.micronews.identity.dto.RegisterRequest;
import com.micronews.identity.dto.UserDto;
import com.micronews.identity.dto.UserAlreadyExistsException;
import com.micronews.identity.dto.InvalidRegistrationDataException;
import com.micronews.infrastructure.config.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class IdentityFacade {
    final UserRepository userRepository;
    final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final Validator validator;
    private final List<Integer> adminIds;

    public IdentityFacade(UserRepository userRepository, LoginRepository loginRepository, PasswordEncoder passwordEncoder, JwtService jwtService, Validator validator) {
        this(userRepository, loginRepository, passwordEncoder, jwtService, validator, java.util.Collections.emptyList());
    }

    public IdentityFacade(UserRepository userRepository, LoginRepository loginRepository, PasswordEncoder passwordEncoder, JwtService jwtService, Validator validator, List<Integer> adminIds) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.validator = validator;
        this.adminIds = adminIds != null ? adminIds : java.util.Collections.emptyList();
    }

    public Optional<UserCredentialsDto> findCredentialsByLogin(String login) {
        return loginRepository.findByLogin(login)
                .map(l -> {
                    String role = (l.idUser != null && adminIds.contains(l.idUser)) ? "ADMIN" : l.getRole();
                    return new UserCredentialsDto(l.getLogin(), l.getPass(), role);
                });
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

    public UserDto register(RegisterRequest request) {
        validateRegistrationData(request);

        if (loginRepository.findByLogin(request.login()).isPresent()) {
            throw new UserAlreadyExistsException(request.login());
        }

        User newUser = new User(null, request.firstname(), request.surname(), request.city());
        User savedUser = userRepository.save(newUser);

        String passwordHash = passwordEncoder.encode(request.password());
        Login newLogin = new Login(request.login(), passwordHash, savedUser.id, "USER");
        loginRepository.save(newLogin);

        return new UserDto(savedUser.id, savedUser.firstname, savedUser.surname, savedUser.city);
    }

    private void validateRegistrationData(RegisterRequest request) {
        if (request == null) {
            throw new InvalidRegistrationDataException("Request cannot be null");
        }
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new InvalidRegistrationDataException(violations.iterator().next().getMessage());
        }
    }
}
