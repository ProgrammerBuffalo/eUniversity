package com.website.eUniversity.model.dto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AttachScheduleDTO {

    private Integer groupId;

    private Integer disciplineId;

    private Integer teacherId;

    @JsonFormat(pattern = "HH:mm", timezone = "GMT+04:00")
    private Date from;

    @JsonFormat(pattern = "HH:mm", timezone = "GMT+04:00")
    private Date to;

    private Integer weekNum;

    private Integer educationalProcessId;

    public AttachScheduleDTO() {

    }

    public AttachScheduleDTO(Integer groupId, Integer disciplineId, Integer teacherId, Date from, Date to, Integer weekNum) {
        this.groupId = groupId;
        this.disciplineId = disciplineId;
        this.teacherId = teacherId;
        this.from = from;
        this.to = to;
        this.weekNum = weekNum;
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

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public Integer getEducationalProcessId() {
        return educationalProcessId;
    }

    public AttachScheduleDTO setEducationalProcessId(Integer educationalProcessId) {
        this.educationalProcessId = educationalProcessId;
        return this;
    }
}
