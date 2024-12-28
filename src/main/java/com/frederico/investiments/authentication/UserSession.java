package com.frederico.investiments.authentication;

import com.frederico.investiments.user.domain.Investor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserSession implements UserDetails {

    private final Investor investor;

    public UserSession(Investor investor) {
        this.investor = investor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public String getPassword() {
        return investor.password();
    }

    public Investor getInvestor() {
        return investor;
    }

    @Override
    public String getUsername() {
        return investor.username();
    }
}
