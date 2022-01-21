package com.website.eUniversity.model.dto.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ScheduleItemDTO {
    private Integer teacherId;
    private String teacherName;

    private Integer weekNum;

    @DateTimeFormat(pattern = "hh:mm")
    private Date from;

    @DateTimeFormat(pattern = "hh:mm")
    private Date to;

    private String type;

    public ScheduleItemDTO() {
    }

    public ScheduleItemDTO(Integer teacherId, String teacherName, Integer weekNum, Date from, Date to, String type) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.weekNum = weekNum;
        this.from = from;
        this.to = to;
        this.type = type;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public ScheduleItemDTO setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public ScheduleItemDTO setTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public Integer getWeekNum() {
        return weekNum;
    }

    public ScheduleItemDTO setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
        return this;
    }

    public Date getFrom() {
        return from;
    }

    public ScheduleItemDTO setFrom(Date from) {
        this.from = from;
        return this;
    }

    public Date getTo() {
        return to;
    }

    public ScheduleItemDTO setTo(Date to) {
        this.to = to;
        return this;
    }

    public String getType() {
        return type;
    }

    public ScheduleItemDTO setType(String type) {
        this.type = type;
        return this;
    }
}
