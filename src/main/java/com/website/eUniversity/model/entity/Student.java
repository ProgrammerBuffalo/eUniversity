package com.website.eUniversity.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "Students")
public class Student {

    @Id
    private String id;

    @Column(unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Student() { }

    public Student(String login, String password, String fullName, Account account) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.account = account;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
