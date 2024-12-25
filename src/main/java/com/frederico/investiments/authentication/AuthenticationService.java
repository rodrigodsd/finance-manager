package com.frederico.investiments.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    String authorize(Authentication authentication);
}
