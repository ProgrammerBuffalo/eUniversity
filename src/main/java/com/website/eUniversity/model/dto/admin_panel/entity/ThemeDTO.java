package com.website.eUniversity.model.dto.admin_panel.entity;

import com.website.eUniversity.model.entity.Theme;

public class ThemeDTO {

    private Integer themeId;

    private String themeName;

    private String originalFileName;

    private Integer materialId;

    private Integer groupId;

    private Integer disciplineId;

    private Integer order;

    public ThemeDTO() {
    }

    public Integer getThemeId() {
        return themeId;
    }

    public ThemeDTO setThemeId(Integer themeId) {
        this.themeId = themeId;
        return this;
    }

    public String getThemeName() {
        return themeName;
    }

    public ThemeDTO setThemeName(String themeName) {
        this.themeName = themeName;
        return this;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public ThemeDTO setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
        return this;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public ThemeDTO setMaterialId(Integer materialId) {
        this.materialId = materialId;
        return this;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public ThemeDTO setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public ThemeDTO setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public ThemeDTO setOrder(Integer order) {
        this.order = order;
        return this;
    }
}
