package com.website.eUniversity.model.dto.admin_panel.entity.base;

public abstract class UserDTO {

    protected String accountId;

    protected Integer userId;

    protected String fullName;

    protected String login;

    protected String password;

    private Integer age;

    public UserDTO() { }

    public UserDTO(String account_id, Integer user_id, String fullName, Integer age, String login, String password) {
        this.accountId = account_id;
        this.userId = user_id;
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.age = age;
    }

    public String getAccountId() {
        return accountId;
    }

    public UserDTO setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserDTO setAge(Integer age) {
        this.age = age;
        return this;
    }
}
