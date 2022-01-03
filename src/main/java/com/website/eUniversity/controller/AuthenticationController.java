package com.website.eUniversity.controller;

import com.website.eUniversity.exception.RefreshTokenExpiredException;
import com.website.eUniversity.exception.RefreshTokenNotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.identification.AuthorizationRequestDTO;
import com.website.eUniversity.model.dto.identification.AuthorizationResponseDTO;
import com.website.eUniversity.service.IAuthenticationService;
import com.website.eUniversity.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@Api(value = "Authentication controller", description = "Designed for authorization and token generation")
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authorize")
    @ApiOperation("Accepts login and password, if the entered data is correct, method returns *Access* and *Refresh* tokens")
    public BaseResponse<AuthorizationResponseDTO> authorize(@RequestBody(required = false) AuthorizationRequestDTO authorizationDTO) {
        return new BaseResponse<AuthorizationResponseDTO>().success(authenticationService.authorize(authorizationDTO),
                "Tokens are created successfully");
    }

    @PostMapping("/refresh-tokens")
    @ApiOperation("Accepts refresh token as Header \"RT\", if refresh token is correct, method returns new *Access* and *Refresh* tokens")
    public BaseResponse<AuthorizationResponseDTO> refreshTokens(@RequestHeader(required = true, name = "Rt") String rt)
            throws RefreshTokenExpiredException, RefreshTokenNotFoundException {
        return new BaseResponse<AuthorizationResponseDTO>().success(authenticationService.refreshToken(rt),
                "Tokens are created successfully");
    }

    @PostMapping("/logout")
    @ApiOperation("For log out (under development)")
    public BaseResponse<String> logout() {
        return null;
    }

}