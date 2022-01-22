package com.website.eUniversity.model.dto.entity;

import java.util.Date;

public class JournalItemDTO {
    private Integer itemId;

    private Integer disciplineId;
    private String disciplineName;

    private Integer teacherId;
    private String teacherName;

    private Integer educationalProcessId;
    private String educationalProcessName;

    private Date date;

    private String progress;

    private String feedback;

    public Integer getItemId() {
        return itemId;
    }

    public JournalItemDTO setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public JournalItemDTO setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
        return this;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public JournalItemDTO setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
        return this;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public JournalItemDTO setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public JournalItemDTO setTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public Integer getEducationalProcessId() {
        return educationalProcessId;
    }

    public JournalItemDTO setEducationalProcessId(Integer educationalProcessId) {
        this.educationalProcessId = educationalProcessId;
        return this;
    }

    public String getEducationalProcessName() {
        return educationalProcessName;
    }

    public JournalItemDTO setEducationalProcessName(String educationalProcessName) {
        this.educationalProcessName = educationalProcessName;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public JournalItemDTO setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getProgress() {
        return progress;
    }

    public JournalItemDTO setProgress(String progress) {
        this.progress = progress;
        return this;
    }

    public String getFeedback() {
        return feedback;
    }

    public JournalItemDTO setFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }
}
