package com.website.eUniversity.model.entity;
import com.website.eUniversity.model.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(columnDefinition="uniqueidentifier")
    private String id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Account() { }

    public Account(String login, String password, String fullName, Integer age, Role role) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
