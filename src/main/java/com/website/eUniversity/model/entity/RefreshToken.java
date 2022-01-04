package com.website.eUniversity.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RefreshTokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "expiredAt")
    private Date expiredAt;

    @Column(name = "isExpired")
    private boolean isExpired;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public RefreshToken() { }

    public RefreshToken(String token, Date createdAt, Date expiredAt, boolean isExpired, Account account) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.isExpired = isExpired;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
