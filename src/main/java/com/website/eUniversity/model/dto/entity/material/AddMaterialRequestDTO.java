package com.website.eUniversity.model.dto.entity.material;

import org.springframework.web.multipart.MultipartFile;

public class AddMaterialRequestDTO {

    private Integer order;

    private String description;

    private MultipartFile multipartFile;

    private String accountId;

    private Integer groupId;

    private Integer disciplineId;

    private Integer educationalProcessId;

    public Integer getOrder() {
        return order;
    }

    public AddMaterialRequestDTO setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddMaterialRequestDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public AddMaterialRequestDTO setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public AddMaterialRequestDTO setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public Integer getEducationalProcessId() {
        return educationalProcessId;
    }

    public AddMaterialRequestDTO setEducationalProcessId(Integer educationalProcessId) {
        this.educationalProcessId = educationalProcessId;
        return this;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public AddMaterialRequestDTO setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public AddMaterialRequestDTO setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
        return this;
    }
}
