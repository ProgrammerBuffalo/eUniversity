package com.website.eUniversity.model.dto;

import com.website.eUniversity.model.dto.IDDLResponseDTO;

public class DDLResponseDTO<T> implements IDDLResponseDTO {
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
