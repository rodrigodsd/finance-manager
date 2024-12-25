package com.frederico.investiments.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationContoller {

    public final AuthenticationService authenticationService;

    public AuthenticationContoller(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("authenticate")
    public String authenticate(Authentication authentication) {
        return authenticationService.authorize(authentication);
    }
}
