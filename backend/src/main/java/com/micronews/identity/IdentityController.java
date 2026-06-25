package com.micronews.identity;

import com.micronews.identity.domain.IdentityFacade;
import org.springframework.web.bind.annotation.RestController;

@RestController
class IdentityController {
    private final IdentityFacade identityFacade;

    public IdentityController(IdentityFacade identityFacade) {
        this.identityFacade = identityFacade;
    }
}
