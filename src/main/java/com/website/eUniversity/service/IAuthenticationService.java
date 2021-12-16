package com.website.eUniversity.service;

import com.website.eUniversity.exception.RefreshTokenExpiredException;
import com.website.eUniversity.exception.RefreshTokenNotFoundException;
import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.TokensDTO;

public interface IAuthenticationService {

    TokensDTO authorize(AuthorizationDTO authorizationDTO);

    TokensDTO refreshToken(String refreshToken) throws RefreshTokenExpiredException, RefreshTokenNotFoundException;
}