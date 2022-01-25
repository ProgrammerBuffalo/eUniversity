package com.website.eUniversity.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Materials")
public class Material {

    @Id
    @Column(columnDefinition="uniqueidentifier")
    private String uuid;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_extension")
    private String fileExtension;

    @ManyToOne
    @JoinColumn(name = "groupDiscipline_id")
    private GroupDiscipline groupDiscipline;

    public Material() {

    }

    public String getUuid() {
        return uuid;
    }

    public Material setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public Material setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public Material setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        return this;
    }

    public GroupDiscipline getGroupDiscipline() {
        return groupDiscipline;
    }

    public Material setGroupDiscipline(GroupDiscipline groupDiscipline) {
        this.groupDiscipline = groupDiscipline;
        return this;
    }
}
