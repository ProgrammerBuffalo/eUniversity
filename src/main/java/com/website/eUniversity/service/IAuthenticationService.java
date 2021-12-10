package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;

public interface IAuthenticationService {

    String authorize(AuthorizationDTO authorizationDTO);

    String refreshToken();
}