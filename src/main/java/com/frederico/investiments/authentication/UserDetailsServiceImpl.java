package com.frederico.investiments.authentication;

import com.frederico.investiments.user.InvestorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public final InvestorRepository investorRepository;

    public UserDetailsServiceImpl(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return investorRepository.findByUsername(username)
                .map(UserSession::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
