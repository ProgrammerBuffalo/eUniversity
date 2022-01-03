package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.exception.RefreshTokenExpiredException;
import com.website.eUniversity.exception.RefreshTokenNotFoundException;
import com.website.eUniversity.model.dto.identification.AuthorizationRequestDTO;
import com.website.eUniversity.model.dto.identification.AuthorizationResponseDTO;

public interface IAuthenticationService {

    AuthorizationResponseDTO authorize(AuthorizationRequestDTO authorizationDTO) throws NotFoundException;

    AuthorizationResponseDTO refreshToken(String refreshToken) throws RefreshTokenExpiredException, RefreshTokenNotFoundException;
}