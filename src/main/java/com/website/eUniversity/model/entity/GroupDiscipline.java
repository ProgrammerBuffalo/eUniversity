package com.website.eUniversity.model.entity;

import com.website.eUniversity.model.dto.entity.GroupDisciplineResponseDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GroupsDisciplines")
public class GroupDiscipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public static GroupDisciplineResponseDTO fromEntityToDto(GroupDiscipline groupDiscipline) {
        return new GroupDisciplineResponseDTO(
                groupDiscipline.discipline.getId(),
                groupDiscipline.teacher.getId(),
                groupDiscipline.discipline.getName(),
                groupDiscipline.teacher.getAccount().getFullName());
    }

    public GroupDiscipline() {

    }

    public GroupDiscipline(Group group, Discipline discipline, Teacher teacher) {
        this.group = group;
        this.discipline = discipline;
        this.teacher = teacher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}