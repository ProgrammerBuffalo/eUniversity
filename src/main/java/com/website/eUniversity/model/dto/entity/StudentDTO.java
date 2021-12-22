package com.website.eUniversity.model.dto.entity;

import com.website.eUniversity.model.dto.entity.base.UserDTO;
import com.website.eUniversity.model.entity.Account;

import javax.persistence.*;

public class StudentDTO extends UserDTO {

    public StudentDTO() { }

    public StudentDTO(String fullName, String login, String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }
}
