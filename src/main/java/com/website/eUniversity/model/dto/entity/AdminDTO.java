package com.website.eUniversity.model.dto.entity;

import com.website.eUniversity.model.dto.entity.base.UserDTO;
import com.website.eUniversity.model.entity.Admin;

public class AdminDTO extends UserDTO {

    public AdminDTO() { }

    public AdminDTO(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }
}
