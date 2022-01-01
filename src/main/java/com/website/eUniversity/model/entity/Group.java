package com.website.eUniversity.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "group_id")
    private List<Student> students;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupDiscipline> groupDisciplines;

    public Group() {
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    // public List<GroupDiscipline> getGroupDisciplines() {
    //     return groupDisciplines;
    // }

    // public void setGroupDisciplines(List<GroupDiscipline> groupDisciplines) {
    //     this.groupDisciplines = groupDisciplines;
    // }
}