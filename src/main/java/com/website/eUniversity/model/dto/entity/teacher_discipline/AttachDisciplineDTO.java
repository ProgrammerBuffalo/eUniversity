package com.website.eUniversity.model.dto.entity.teacher_discipline;

public class AttachDisciplineDTO {
    Integer teacherId;

    Integer disciplineId;

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }
}
