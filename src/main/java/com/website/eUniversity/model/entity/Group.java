package com.website.eUniversity.model.entity;

import com.website.eUniversity.model.dto.entity.DisciplineDTO;
import com.website.eUniversity.model.dto.entity.group.GroupDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "createdAt")
    private Date date = new Date();

    @OneToMany
    @JoinColumn(name = "group_id")
    private List<Student> students;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupDiscipline> groupDisciplines;

    public Group() {
    }

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GroupDTO toDTO(Group group) {
        return new GroupDTO(
                group.getId(),
                group.getName(),
                group.getDate());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<GroupDiscipline> getGroupDisciplines() {
        return groupDisciplines;
    }

    public void setGroupDisciplines(List<GroupDiscipline> groupDisciplines) {
        this.groupDisciplines = groupDisciplines;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}