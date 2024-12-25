package com.frederico.investiments.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;

    public AuthenticationServiceImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public String authorize(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }
}
