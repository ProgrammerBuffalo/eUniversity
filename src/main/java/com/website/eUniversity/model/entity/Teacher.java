package com.website.eUniversity.model.entity;


import com.website.eUniversity.model.dto.admin_panel.entity.TeacherDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<GroupDiscipline> groupDisciplines;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<TeacherDiscipline> teacherDisciplines;

    public static TeacherDTO toDTO(Teacher teacher) {
        return new TeacherDTO(
                teacher.getAccount().getId(),
                teacher.getId(),
                teacher.getAccount().getFullName(),
                teacher.getAccount().getAge(),
                teacher.getAccount().getLogin(),
                teacher.getAccount().getPassword());
    }

    public Teacher() { }

    public Teacher(Account account) {
        this.account = account;
    }

    public Teacher(Account account, List<GroupDiscipline> groupDisciplines) {
        this.account = account;
        this.groupDisciplines = groupDisciplines;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<GroupDiscipline> getGroupDisciplines() {
        return groupDisciplines;
    }

    public void setGroupDisciplines(List<GroupDiscipline> groupDisciplines) {
        this.groupDisciplines = groupDisciplines;
    }
}