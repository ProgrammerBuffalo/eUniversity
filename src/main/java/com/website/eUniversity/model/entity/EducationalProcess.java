package com.website.eUniversity.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EducationalProcesses")
public class EducationalProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public EducationalProcess() {

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

    public void setName(String name) {
        this.name = name;
    }

}
