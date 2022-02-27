package com.website.eUniversity.model.entity;

import com.website.eUniversity.model.dto.admin_panel.entity.StudentDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne
    @JoinColumn(name = "journal_id", referencedColumnName = "id")
    private Journal journal;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentMaterial> studentMaterial;

    public Student() { }

    public Student(Account account) {
        this.account = account;
    }

    public static StudentDTO toDTO(Student student) {
        return new StudentDTO(
                student.getAccount().getId(),
                student.getId(),
                student.getAccount().getFullName(),
                student.getAccount().getAge(),
                student.getAccount().getLogin(),
                student.getAccount().getPassword(),
                Optional.ofNullable(student.getGroup()).map(Group::getName).orElse("Without group")
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public List<StudentMaterial> getStudentMaterial() {
        return studentMaterial;
    }

    public Student setStudentMaterial(List<StudentMaterial> studentMaterial) {
        this.studentMaterial = studentMaterial;
        return this;
    }
}
