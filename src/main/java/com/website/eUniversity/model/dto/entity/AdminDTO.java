package com.website.eUniversity.model.dto.entity;

import com.website.eUniversity.model.dto.entity.base.UserDTO;

public class AdminDTO extends UserDTO {

    public AdminDTO() { }

    public AdminDTO(String id, String fullName, String login, String password) {
        this.id = id;
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }
}
