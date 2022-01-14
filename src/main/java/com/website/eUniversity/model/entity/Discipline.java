package com.website.eUniversity.model.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Disciplines")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "shortName", unique = true)
    private String shortName;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<TeacherDiscipline> teacherDisciplines;

    @OneToMany
    @Transient
    private List<GroupDiscipline> groupDisciplines;

    public Discipline() {

    }

    public Discipline(Integer id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
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

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public List<GroupDiscipline> getGroupDisciplines() {
        return groupDisciplines;
    }

    public void setGroupDisciplines(List<GroupDiscipline> groupDisciplines) {
        this.groupDisciplines = groupDisciplines;
    }
}