package com.website.eUniversity.model.dto.admin_panel.entity;

import com.website.eUniversity.model.dto.admin_panel.entity.base.UserDTO;

public class StudentDTO extends UserDTO {

    private String group;

    public StudentDTO() { }

    public StudentDTO(String account_id, Integer user_id, String fullName, Integer age, String login, String password) {
        super(account_id, user_id, fullName, age, login, password);
    }

    public StudentDTO(String account_id, Integer user_id, String fullName, Integer age, String login, String password, String group) {
        super(account_id, user_id, fullName, age, login, password);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public StudentDTO setGroup(String group) {
        this.group = group;
        return this;
    }
}
