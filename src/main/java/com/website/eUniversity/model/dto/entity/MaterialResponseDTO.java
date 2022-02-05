package com.website.eUniversity.model.dto.entity;

public class MaterialResponseDTO {

    private Integer id;

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
}
