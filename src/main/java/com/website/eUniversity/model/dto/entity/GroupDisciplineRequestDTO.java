package com.website.eUniversity.model.dto.entity;

import java.util.Date;

public class GroupDisciplineRequestDTO {

    private Integer groupId;

    private Integer disciplineId;

    private Integer teacherId;

    public GroupDisciplineRequestDTO(Integer groupId, Integer disciplineId, Integer teacherId) {
        this.groupId = groupId;
        this.disciplineId = disciplineId;
        this.teacherId = teacherId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
