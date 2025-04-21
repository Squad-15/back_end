package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String numberRegister) throws UsernameNotFoundException {
        return userAccountRepository.findByNumberRegister(numberRegister);
    }
}
