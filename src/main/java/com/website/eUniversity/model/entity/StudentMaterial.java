package com.website.eUniversity.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "StudentsMaterials")
public class StudentMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id")
    private Material material;

    public StudentMaterial() {

    }

    public Integer getId() {
        return id;
    }

    public StudentMaterial setId(Integer id) {
        this.id = id;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public StudentMaterial setStudent(Student student) {
        this.student = student;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public StudentMaterial setMaterial(Material material) {
        this.material = material;
        return this;
    }

}
