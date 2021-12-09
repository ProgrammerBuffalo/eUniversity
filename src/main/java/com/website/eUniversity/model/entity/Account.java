package com.website.eUniversity.model.entity;
import com.website.eUniversity.model.Role;

import javax.persistence.*;

@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    private String id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
