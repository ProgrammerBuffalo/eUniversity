package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.service.IAccountDetailsService;
import com.website.eUniversity.service.IAuthenticationService;
import com.website.eUniversity.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService implements IAuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAccountDetailsService accountDetailsService;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public String authorize(AuthorizationDTO authorizationDTO) {
        Authentication authentication = new
                UsernamePasswordAuthenticationToken(authorizationDTO.getLogin(), authorizationDTO.getPassword());

        authenticationManager.authenticate(authentication);

        UserDetails userDetails = accountDetailsService.loadUserByUsername(authentication.getPrincipal().toString());

        String jwt = jwtTokenUtil.generateToken(userDetails);

        return jwt;
    }

    @Override
    public String refreshToken() {

        return null;
    }

}
