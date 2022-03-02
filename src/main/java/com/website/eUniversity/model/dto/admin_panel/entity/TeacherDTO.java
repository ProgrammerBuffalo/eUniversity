package com.website.eUniversity.model.dto.admin_panel.entity;

import com.website.eUniversity.model.dto.admin_panel.entity.base.UserDTO;

public class TeacherDTO extends UserDTO {

    public TeacherDTO() { }

    public TeacherDTO(String account_id, Integer user_id, String fullName, Integer age, String login, String password) {
        super(account_id, user_id, fullName, age, login, password);
    }
}
