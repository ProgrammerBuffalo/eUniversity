package com.website.eUniversity.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_path")
    private String filePath;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @OneToOne(mappedBy = "file", cascade = CascadeType.ALL)
    private Material material;

    public File() {

    }

    public Integer getId() {
        return id;
    }

    public File setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public File setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public File setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public File setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public File setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public File setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public File setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
        return this;
    }
}
