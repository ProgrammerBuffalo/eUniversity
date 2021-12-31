package com.website.eUniversity.service.base;

import com.website.eUniversity.model.dto.entity.base.UserDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;

import java.util.List;

public interface IUserService <T extends UserDTO> {
    T register(RegistrationDTO registrationDTO);

    void delete(String uuid);

    List<T> getUserList();
}
