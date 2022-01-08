package com.website.eUniversity.model.dto.entity.discipline;

public class AddDisciplineDTO {
    String name;

    String shortName;

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
