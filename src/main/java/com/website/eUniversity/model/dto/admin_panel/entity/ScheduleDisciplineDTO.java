package com.website.eUniversity.model.dto.admin_panel.entity;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDisciplineDTO {
    private Integer disciplineId;
    private String disciplineName;

    private List<ScheduleItemDTO> itemList;

    public ScheduleDisciplineDTO() {
        itemList = new ArrayList<>();
    }

    public ScheduleDisciplineDTO(Integer disciplineId, String disciplineName, List<ScheduleItemDTO> itemList) {
        this.disciplineId = disciplineId;
        this.disciplineName = disciplineName;
        this.itemList = itemList;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public ScheduleDisciplineDTO setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
        return this;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public ScheduleDisciplineDTO setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
        return this;
    }

    public List<ScheduleItemDTO> getItemList() {
        return itemList;
    }

    public ScheduleDisciplineDTO setItemList(List<ScheduleItemDTO> itemList) {
        this.itemList = itemList;
        return this;
    }
}
