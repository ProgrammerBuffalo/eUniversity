package com.website.eUniversity.model.dto.admin_panel.identification;

import com.website.eUniversity.model.Role;

public class AuthorizationResponseDTO {

    private String id;

    private String fullName;

    private Role role;

    private String jwtToken;

    private String refreshToken;

    public AuthorizationResponseDTO(String id, String fullName, Role role, String jwtToken, String refreshToken) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
