package com.website.eUniversity.model.entity;

import com.website.eUniversity.model.dto.admin_panel.entity.MaterialResponseDTO;

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

    @Column(name = "user_id")
    private String userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private File file;

    @ManyToOne
    @JoinColumn(name = "groupDiscipline_id")
    private GroupDiscipline groupDiscipline;

    @ManyToOne
    @JoinColumn(name = "educationalProcess_id")
    private EducationalProcess educationalProcess;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<StudentMaterial> studentMaterials;

    @OneToMany(mappedBy = "material")
    private List<Theme> themes;

    @Transient
    private String userName;

    public Material() {

    }

    public Material(Integer id, Integer order, String description, String userId, GroupDiscipline groupDiscipline,
                    EducationalProcess educationalProcess) {
        this.description = description;
        this.id = id;
        this.order = order;
        this.userId = userId;
        this.groupDiscipline = groupDiscipline;
        this.educationalProcess = educationalProcess;
    }

    public Material(Integer id, Integer order, File file, String description, String accountId, String userName,
                    GroupDiscipline groupDiscipline, EducationalProcess educationalProcess) {
        this.id = id;
        this.order = order;
        this.file = file;
        this.description = description;
        this.groupDiscipline = groupDiscipline;
        this.educationalProcess = educationalProcess;
        this.userId = accountId;
        this.userName = userName;
    }

    public static MaterialResponseDTO toDTO(Material material) {
        return new MaterialResponseDTO()
                .setId(material.getId())
                .setFileName(material.getFile().getOriginalFileName())
                .setDescription(material.getDescription())
                .setEducationalProcess(material.getEducationalProcess().getName())
                .setAccountId(material.getUserId())
                .setUserFullName(material.getUserName())
                .setOrder(material.getOrder());
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

    public Material setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public Material setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Material setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public Material setThemes(List<Theme> themes) {
        this.themes = themes;
        return this;
    }
}
