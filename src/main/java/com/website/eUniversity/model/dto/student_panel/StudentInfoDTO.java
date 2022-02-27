package com.website.eUniversity.model.dto.student_panel;

import com.website.eUniversity.model.entity.Group;
import com.website.eUniversity.model.entity.Student;

import java.util.Objects;
import java.util.Optional;

public class StudentInfoDTO {

    private String fullName;

    private Integer age;

    private String group;

    public StudentInfoDTO() {
    }

    public static StudentInfoDTO toDTO(Student student) {
        return new StudentInfoDTO().setFullName(student.getAccount().getFullName())
                                    .setGroup(student.getGroup() == null ? "NON_GROUP" : student.getGroup().getName())
                                    .setAge(student.getAccount().getAge());
    }

    public String getFullName() {
        return fullName;
    }

    public StudentInfoDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public StudentInfoDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public StudentInfoDTO setGroup(String group) {
        this.group = group;
        return this;
    }
}
