package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.IDDLResponseDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.teacher_discipline.AttachDisciplineDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.teacher_discipline.ITeacherDisciplineDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.teacher_discipline.ITeacherShortDisciplinesDTO;

import java.util.List;

public interface ITeacherDisciplineService {

    List<ITeacherShortDisciplinesDTO> getTeachersShortDisciplines();

    List<ITeacherDisciplineDTO> getTeacherDisciplines(Integer teacherId);

    ITeacherShortDisciplinesDTO getTeacherShortDisciplines(Integer teacherId);

    List<IDDLResponseDTO<Integer>> getTeacherDisciplineNames(Integer teacherId);

    void attachDiscipline(AttachDisciplineDTO dto);

    void detachDiscipline(Integer teacherId, Integer disciplineId);
}