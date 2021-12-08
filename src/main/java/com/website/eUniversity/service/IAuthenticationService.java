package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.JwtDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthenticationService extends UserDetailsService {
    void authenticate(AuthorizationDTO authorizationDTO);

    UserDetails getUserDetails(String login);

    JwtDTO register(RegistrationDTO registrationDTO);
    
    JwtDTO authorize(AuthorizationDTO authorizationDTO);
    
    JwtDTO refreshToken();
}
