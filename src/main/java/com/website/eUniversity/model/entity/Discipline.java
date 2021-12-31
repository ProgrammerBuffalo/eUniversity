package com.website.eUniversity.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Disciplines")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @Transient
    private List<GroupDiscipline> groupDisciplines;

    public Discipline() {

    }

    public Discipline(int id, String name) {
        this.id = id;
        this.name = name;
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

    public List<GroupDiscipline> getGroupDisciplines() {
        return groupDisciplines;
    }

    public void setGroupDisciplines(List<GroupDiscipline> groupDisciplines) {
        this.groupDisciplines = groupDisciplines;
    }

    // public List<GroupDiscipline> getGroupDisciplines() {
    //      return groupDisciplines;
    // }

    // public void setGroupDisciplines(List<GroupDiscipline> groupDisciplines) {
//    }
}
