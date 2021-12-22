package com.website.eUniversity.model.dto.entity;

import com.website.eUniversity.model.dto.entity.base.UserDTO;

public class TeacherDTO extends UserDTO {

    public TeacherDTO() { }

    public TeacherDTO(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }
}
