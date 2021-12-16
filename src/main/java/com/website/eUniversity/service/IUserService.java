package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.identification.RegistrationDTO;

public interface IUserService {

    void register(RegistrationDTO registrationDTO);

    void delete(String uuid);
}
