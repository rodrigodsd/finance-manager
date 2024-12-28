package com.frederico.investiments.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationContoller {

    // TODO treat exception for expired token to drop exception and externalized as response

    public final AuthenticationService authenticationService;

    public AuthenticationContoller(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("authenticate")
    public String authenticate(Authentication authentication) {
        return authenticationService.authorize(authentication);
    }
}
