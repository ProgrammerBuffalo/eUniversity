package com.website.eUniversity.model.dto.identification;

public class RegistrationDTO {
    private String login;

    private String password;

    private String fullName;

    private int age;

    public RegistrationDTO() { }

    public RegistrationDTO(String login, String password, String fullName, int age) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
