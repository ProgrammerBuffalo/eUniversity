package com.website.eUniversity.model.dto.entity;

import com.website.eUniversity.model.dto.entity.base.UserDTO;

public class StudentDTO extends UserDTO {

    public StudentDTO() { }

    public StudentDTO(String id, String fullName, String login, String password) {
        this.id = id;
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }
}
