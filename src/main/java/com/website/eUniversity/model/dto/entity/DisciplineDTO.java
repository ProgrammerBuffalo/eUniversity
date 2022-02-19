package com.website.eUniversity.model.dto.entity;

public class DisciplineDTO {
    Integer id;

    String name;

    String shortName;

    public DisciplineDTO(Integer id, String name, String shortName){
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
