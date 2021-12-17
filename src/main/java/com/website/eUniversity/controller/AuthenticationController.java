package com.website.eUniversity.controller;

import com.website.eUniversity.exception.RefreshTokenExpiredException;
import com.website.eUniversity.exception.RefreshTokenNotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.TokensDTO;
import com.website.eUniversity.service.IAuthenticationService;
import com.website.eUniversity.util.JwtTokenUtil;
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
    public BaseResponse<TokensDTO> authorize(@RequestBody(required = false) AuthorizationDTO authorizationDTO) {
        return new BaseResponse<TokensDTO>().success(authenticationService.authorize(authorizationDTO),
                "Tokens are created successfully");
    }

    @PostMapping("/refresh-tokens")
    public BaseResponse<TokensDTO> refreshTokens(@RequestHeader(required = true, name = "Rt") String rt)
            throws RefreshTokenExpiredException, RefreshTokenNotFoundException {
        return new BaseResponse<TokensDTO>().success(authenticationService.refreshToken(rt),
                "Tokens are created successfully");
    }

    @PostMapping("/logout")
    public BaseResponse<String> logout() {
        return null;
    }

}