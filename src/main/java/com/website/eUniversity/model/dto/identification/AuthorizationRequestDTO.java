package com.website.eUniversity.model.dto.identification;

public class AuthorizationRequestDTO {

    private String login;

    private String password;

    public AuthorizationRequestDTO(String login, String password) {
        this.login = login;
        this.password = password;
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
}