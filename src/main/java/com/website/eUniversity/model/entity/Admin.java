package com.website.eUniversity.model.entity;

import com.website.eUniversity.model.dto.entity.AdminDTO;
import com.website.eUniversity.model.dto.entity.StudentDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "account_id")
    private Account account;

    public static AdminDTO toDTO(Admin admin) {
        return new AdminDTO(
                admin.getAccount().getId(),
                admin.getId(),
                admin.getAccount().getFullName(),
                admin.getAccount().getAge(),
                admin.getAccount().getLogin(),
                admin.getAccount().getPassword());
    }

    public Admin() { }

    public Admin(Account account) {
        this.account = account;
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
}
