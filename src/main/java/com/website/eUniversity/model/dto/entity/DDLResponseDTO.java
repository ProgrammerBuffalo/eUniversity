package com.website.eUniversity.model.dto.entity;

public class DDLResponseDTO<T> {
    public T id;

    public String name;

    public DDLResponseDTO(T id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
