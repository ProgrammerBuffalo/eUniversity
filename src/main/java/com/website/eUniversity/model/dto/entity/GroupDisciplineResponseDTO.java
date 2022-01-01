package com.website.eUniversity.model.dto.entity;

public class GroupDisciplineResponseDTO {

    private String groupName;

    private String disciplineName;

    private String teacherName;

    public GroupDisciplineResponseDTO(String groupName, String disciplineName, String teacherName) {
        this.groupName = groupName;
        this.disciplineName = disciplineName;
        this.teacherName = teacherName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
}
