package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.entity.DDLResponseDTO;
import com.website.eUniversity.model.dto.entity.TeacherDisciplineNamesDTO;
import com.website.eUniversity.model.dto.entity.teacher_discipline.AttachDisciplineDTO;
import com.website.eUniversity.model.dto.entity.teacher_discipline.ITeacherDisciplineDTO;
import com.website.eUniversity.model.dto.entity.teacher_discipline.ITeacherShortDisciplinesDTO;
import com.website.eUniversity.model.entity.TeacherDiscipline;

import java.util.List;

public interface ITeacherDisciplineService {

    List<ITeacherShortDisciplinesDTO> getTeachersShortDisciplines();

    List<ITeacherDisciplineDTO> getTeacherDisciplines(Integer teacherId);

    ITeacherShortDisciplinesDTO getTeacherShortDisciplines(Integer teacherId);

    List<DDLResponseDTO<Integer>> getTeacherDisciplineNames(Integer teacherId);

    void attachDiscipline(AttachDisciplineDTO dto);

    void detachDiscipline(Integer teacherId, Integer disciplineId);
}