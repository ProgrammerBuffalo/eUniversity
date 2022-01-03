package com.website.eUniversity.model.dto.entity;

import com.website.eUniversity.model.dto.entity.base.UserDTO;

public class AdminDTO extends UserDTO {

    public AdminDTO() { }

    public AdminDTO(String id, String fullName, Integer age, String login, String password) {
        super(id, fullName, age, login, password);
    }
}
