package com.website.eUniversity.model.dto.entity.material;

import com.website.eUniversity.model.dto.PaginationDTO;

public class GetMaterialRequestDTO {
    Integer groupId;

    Integer disciplineId;

    PaginationDTO pagination;

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    public PaginationDTO getPagination() {
        return pagination;
    }
}
