package com.website.eUniversity.controller;

import com.website.eUniversity.exception.TokenExpiredException;
import com.website.eUniversity.exception.TokenNotFoundException;
import com.website.eUniversity.model.client.Response;
import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.TokensDTO;
import com.website.eUniversity.service.IAuthenticationService;
import com.website.eUniversity.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Response<TokensDTO> authorize(@RequestBody(required = false) AuthorizationDTO authorizationDTO) {
        return new Response<TokensDTO>().success(authenticationService.authorize(authorizationDTO),
                "Tokens are created successfully");
    }

    @PostMapping("/refresh-tokens")
    public Response<TokensDTO> refreshTokens(@RequestHeader(required = true, name = "Rt") String rt)
            throws TokenExpiredException, TokenNotFoundException {
        return new Response<TokensDTO>().success(authenticationService.refreshToken(rt),
                "Tokens are created successfully");
    }

    @PostMapping("/logout")
    public Response<String> logout() {
        return null;
    }

}