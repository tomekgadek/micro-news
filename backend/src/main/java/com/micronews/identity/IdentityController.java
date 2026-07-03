package com.micronews.identity;

import com.micronews.identity.domain.IdentityFacade;
import com.micronews.identity.dto.LoginRequest;
import com.micronews.identity.dto.LoginResponse;
import com.micronews.identity.dto.RegisterRequest;
import com.micronews.identity.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class IdentityController {
    private final IdentityFacade identityFacade;

    public IdentityController(IdentityFacade identityFacade) {
        this.identityFacade = identityFacade;
    }

    @PostMapping("/identity/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = identityFacade.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/identity/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest registerRequest) {
        UserDto response = identityFacade.register(registerRequest);
        return ResponseEntity.ok(response);
    }
}
