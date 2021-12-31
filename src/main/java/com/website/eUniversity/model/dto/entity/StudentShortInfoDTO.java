package com.website.eUniversity.model.dto.entity;

public class StudentShortInfoDTO {

    private String fullName;

    public StudentShortInfoDTO(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
