package com.website.eUniversity.model.dto.entity.base;

public abstract class UserDTO {

    protected String id;

    protected String fullName;

    protected String login;

    protected String password;

    private Integer age;

    public UserDTO() { }

    public UserDTO(String id, String fullName, Integer age, String login, String password) {
        this.id = id;
        this.fullName = fullName;
        this.login = login;
        this.password = password;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
