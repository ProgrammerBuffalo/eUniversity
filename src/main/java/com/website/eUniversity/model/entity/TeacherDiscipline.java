package com.website.eUniversity.model.entity;


import com.website.eUniversity.model.dto.entity.DDLResponseDTO;
import com.website.eUniversity.model.dto.entity.GroupDisciplineResponseDTO;
import com.website.eUniversity.model.dto.entity.TeacherDisciplineNamesDTO;

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

    public static DDLResponseDTO<Integer> fromEntityToDtoDDL(TeacherDiscipline groupDiscipline) {
        return new DDLResponseDTO<>(groupDiscipline.discipline.getId(), groupDiscipline.discipline.getName());
    }

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
