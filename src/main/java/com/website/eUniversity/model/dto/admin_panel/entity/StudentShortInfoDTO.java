package com.website.eUniversity.model.dto.admin_panel.entity;

public class StudentShortInfoDTO {

    private Integer id;

    private String fullName;

    public StudentShortInfoDTO(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
