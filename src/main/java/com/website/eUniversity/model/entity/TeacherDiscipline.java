package com.website.eUniversity.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "TeacherDiscipline")
public class TeacherDiscipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    public TeacherDiscipline() {

    }

    public TeacherDiscipline(Integer id, Teacher teacher, Discipline discipline) {
        this.id = id;
        this.teacher = teacher;
        this.discipline = discipline;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Discipline getDiscipline() {
        return discipline;
    }
}
