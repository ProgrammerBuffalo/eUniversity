package com.website.eUniversity.model.dto.entity;

public class TeacherDisciplineNamesDTO {

    private Integer disciplineId;

    private String disciplineName;

    public TeacherDisciplineNamesDTO(Integer disciplineId, String disciplineName) {
        this.disciplineId = disciplineId;
        this.disciplineName = disciplineName;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setTeacherName(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }
}