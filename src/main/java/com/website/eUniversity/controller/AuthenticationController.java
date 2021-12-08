package com.website.eUniversity.controller;

import com.website.eUniversity.model.client.Response;
import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.JwtDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.service.IAuthenticationService;
import com.website.eUniversity.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public Response<JwtDTO> registrate(RegistrationDTO registrationDTO) {
        return null;
    }

    public Response<String> authorize(AuthorizationDTO authorizationDTO) {
        try {
            authenticationService.authenticate(authorizationDTO);
        }
        catch (BadCredentialsException ex) {
            return new Response<JwtDTO>().error("Token is not valid", 404);
        }

        UserDetails userDetails = authenticationService.getUserDetails(authorizationDTO.getLogin());

        String jwt = jwtTokenUtil.generateToken(userDetails);

        return new Response<String>().success(jwt, "Token successfully generated");
    }
}
