package com.website.eUniversity.model.dto.entity.material;

public class MaterialResponseDTO {

    private Integer id;

    private String accountId;

    private String userFullName;

    private Integer order;

    private String description;

    private String educationalProcess;

    private String fileName;

    public Integer getId() {
        return id;
    }

    public MaterialResponseDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MaterialResponseDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public MaterialResponseDTO setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getEducationalProcess() {
        return educationalProcess;
    }

    public MaterialResponseDTO setEducationalProcess(String educationalProcess) {
        this.educationalProcess = educationalProcess;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public MaterialResponseDTO setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public MaterialResponseDTO setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public MaterialResponseDTO setUserFullName(String userFullName) {
        this.userFullName = userFullName;
        return this;
    }
}
