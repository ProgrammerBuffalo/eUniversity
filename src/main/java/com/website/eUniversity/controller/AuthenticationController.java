package com.website.eUniversity.controller;

import com.website.eUniversity.model.client.Response;
import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.service.IAuthenticationService;
import com.website.eUniversity.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/authorize")
    public Response<String> authorize(@RequestBody(required = false) AuthorizationDTO authorizationDTO) {
        try {
            return new Response<String>().success(authenticationService.authorize(authorizationDTO),
                    "Token is created successfully");
        }
        catch (BadCredentialsException ex) {
            return new Response<String>().error("Token is not valid", 404);
        }
    }

}