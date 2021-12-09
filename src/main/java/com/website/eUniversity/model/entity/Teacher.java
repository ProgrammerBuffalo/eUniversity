package com.website.eUniversity.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "Teachers")
public class Teacher {

    @Id
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Teacher() { }

    public Teacher(String fullName, Account account) {
        this.fullName = fullName;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
