package com.website.eUniversity.service.base;

import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.base.UserDTO;
import com.website.eUniversity.model.dto.admin_panel.identification.RegistrationDTO;

public interface IUserService <T extends UserDTO> {
    T register(RegistrationDTO registrationDTO);

    T update(T user);

    String delete(String uuid);

    PaginatedListDTO getUserList(String search, Integer pageIndex, Integer pageSize);
}
