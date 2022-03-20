package com.website.eUniversity.model.entity;

import com.website.eUniversity.model.dto.admin_panel.entity.JournalItemDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "JournalItems")
public class JournalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "assess")
    private Integer assess;

    @Column(name = "attendance")
    private Boolean isPresent;

    @Column(name = "feedback")
    private String feedback;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public static JournalItemDTO fromEntityToDto(JournalItem journal) {
        return new JournalItemDTO()
                .setItemId(journal.getId())
                .setDisciplineId(journal.getSchedule().getGroupDiscipline().getDisciplineId())
                .setDisciplineName(journal.getSchedule().getGroupDiscipline().getDiscipline().getName())
                .setTeacherId(journal.getSchedule().getGroupDiscipline().getTeacher().getId())
                .setTeacherName(journal.getSchedule().getGroupDiscipline().getTeacher().getAccount().getFullName())
                .setEducationalProcessId(journal.getSchedule().getEducationalProcess().getId())
                .setEducationalProcessName(journal.getSchedule().getEducationalProcess().getName())
                .setDate(journal.getDate())
                .setDayNumber(journal.getSchedule().getDay())
                .setAssess(journal.getAssess())
                .setPresent(journal.getPresent())
                .setFeedback(journal.getFeedback());
    }

    public JournalItem() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getFeedback() {
        return feedback;
    }

    public JournalItem setFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public JournalItem setPresent(Boolean present) {
        isPresent = present;
        return this;
    }

    public Integer getAssess() {
        return assess;
    }

    public JournalItem setAssess(Integer assess) {
        this.assess = assess;
        return this;
    }
}
