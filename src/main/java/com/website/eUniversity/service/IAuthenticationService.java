package com.website.eUniversity.service;

import com.website.eUniversity.exception.TokenExpiredException;
import com.website.eUniversity.exception.TokenNotFoundException;
import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.model.dto.identification.TokensDTO;

public interface IAuthenticationService {

    TokensDTO authorize(AuthorizationDTO authorizationDTO);

    TokensDTO refreshToken(String refreshToken) throws TokenExpiredException, TokenNotFoundException;
}