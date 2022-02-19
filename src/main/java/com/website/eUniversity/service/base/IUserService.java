package com.website.eUniversity.service.base;

import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.PaginationDTO;
import com.website.eUniversity.model.dto.entity.base.UserDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;

import java.util.List;

public interface IUserService <T extends UserDTO> {
    T register(RegistrationDTO registrationDTO);

    T update(T user);

    String delete(String uuid);

    PaginatedListDTO getUserList(PaginationDTO dto);
}
