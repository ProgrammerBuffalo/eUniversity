package com.website.eUniversity.model.dto.admin_panel.entity;

public class GroupDisciplineResponseDTO {

    private Integer disciplineId;

    private Integer teacherId;

    private String disciplineName;

    private String teacherName;

    public GroupDisciplineResponseDTO(Integer disciplineId, Integer teacherId, String disciplineName, String teacherName) {
        this.disciplineId = disciplineId;
        this.teacherId = teacherId;
        this.disciplineName = disciplineName;
        this.teacherName = teacherName;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
