package com.website.eUniversity.model.dto.entity.base;

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
