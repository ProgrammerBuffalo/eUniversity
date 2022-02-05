package com.website.eUniversity.model.entity;

import com.website.eUniversity.model.dto.entity.MaterialResponseDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Materials")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "[ORDER]")
    private Integer order;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private File file;

    @ManyToOne
    @JoinColumn(name = "groupDiscipline_id")
    private GroupDiscipline groupDiscipline;

    @ManyToOne
    @JoinColumn(name = "educationalProcess_id")
    private EducationalProcess educationalProcess;

    @OneToMany(mappedBy = "material")
    private List<StudentMaterial> studentMaterials;

    public Material() {

    }

    public Material(Integer order, File file, GroupDiscipline groupDiscipline, EducationalProcess educationalProcess) {
        this.order = order;
        this.file = file;
        this.groupDiscipline = groupDiscipline;
        this.educationalProcess = educationalProcess;
    }

    public static MaterialResponseDTO toDTO(Material material) {
        return new MaterialResponseDTO().setId(material.id)
                .setFileName(material.file.getFileName())
                .setDescription(material.description)
                .setEducationalProcess(material.educationalProcess.getName());
    }

    public Integer getId() {
        return id;
    }

    public Material setId(Integer id) {
        this.id = id;
        return this;
    }

    public File getFile() {
        return file;
    }

    public Material setFile(File file) {
        this.file = file;
        return this;
    }

    public GroupDiscipline getGroupDiscipline() {
        return groupDiscipline;
    }

    public Material setGroupDiscipline(GroupDiscipline groupDiscipline) {
        this.groupDiscipline = groupDiscipline;
        return this;
    }

    public EducationalProcess getEducationalProcess() {
        return educationalProcess;
    }

    public Material setEducationalProcess(EducationalProcess educationalProcess) {
        this.educationalProcess = educationalProcess;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public Material setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public List<StudentMaterial> getStudentMaterials() {
        return studentMaterials;
    }

    public Material setStudentMaterials(List<StudentMaterial> studentMaterial) {
        this.studentMaterials = studentMaterial;
        return this;
    }
}
