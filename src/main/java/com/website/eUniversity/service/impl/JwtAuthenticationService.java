package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.JwtDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.service.IAuthenticationService;
import com.website.eUniversity.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtAuthenticationService implements IAuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public JwtDTO register(RegistrationDTO registrationDTO) {
        return null;
    }

    @Override
    public JwtDTO authorize(AuthorizationDTO authorizationDTO) {
        return null;
    }

    @Override
    public JwtDTO refreshToken() {

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("eldar", "qwerty", new ArrayList<>());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
