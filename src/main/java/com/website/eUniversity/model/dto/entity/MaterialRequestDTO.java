package com.website.eUniversity.model.dto.entity;

import org.springframework.web.multipart.MultipartFile;

public class MaterialRequestDTO {

    private Integer order;

    private String description;

    private MultipartFile multipartFile;

    private String accountId;

    private GroupDisciplineRequestDTO groupDisciplineRequestDTO;

    private Integer educationalProcessId;

    public Integer getOrder() {
        return order;
    }

    public MaterialRequestDTO setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MaterialRequestDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public MaterialRequestDTO setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public MaterialRequestDTO setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public GroupDisciplineRequestDTO getGroupDisciplineRequestDTO() {
        return groupDisciplineRequestDTO;
    }

    public MaterialRequestDTO setGroupDisciplineRequestDTO(GroupDisciplineRequestDTO groupDisciplineRequestDTO) {
        this.groupDisciplineRequestDTO = groupDisciplineRequestDTO;
        return this;
    }

    public Integer getEducationalProcessId() {
        return educationalProcessId;
    }

    public MaterialRequestDTO setEducationalProcessId(Integer educationalProcessId) {
        this.educationalProcessId = educationalProcessId;
        return this;
    }
}
