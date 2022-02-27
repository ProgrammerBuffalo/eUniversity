package com.website.eUniversity.model.entity;

import com.website.eUniversity.model.dto.admin_panel.entity.ThemeDTO;

import javax.persistence.*;

@Entity
@Table(name = "Themes")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "isComplete")
    private Boolean isCompleted = false;

    @ManyToOne
    private Material material;

    @ManyToOne
    private GroupDiscipline groupDiscipline;

    public static ThemeDTO toDTO(Theme theme) {
        return new ThemeDTO().setThemeId(theme.getId())
                .setThemeName(theme.getName())
                .setOriginalFileName(theme.getMaterial().getFile().getOriginalFileName())
                .setCompleted(theme.getCompleted())
                .setGroupId(theme.getGroupDiscipline().getGroup().getId())
                .setTeacherId(theme.getGroupDiscipline().getTeacher().getId())
                .setMaterialId(theme.getMaterial().getId());
    }

    public Theme() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Theme setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public Theme setCompleted(Boolean completed) {
        isCompleted = completed;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public Theme setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public GroupDiscipline getGroupDiscipline() {
        return groupDiscipline;
    }

    public Theme setGroupDiscipline(GroupDiscipline groupDiscipline) {
        this.groupDiscipline = groupDiscipline;
        return this;
    }
}