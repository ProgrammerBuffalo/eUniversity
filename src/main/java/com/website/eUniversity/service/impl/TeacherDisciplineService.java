package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.dto.entity.teacher_discipline.AttachDisciplineDTO;
import com.website.eUniversity.model.dto.entity.teacher_discipline.ITeacherDisciplineDTO;
import com.website.eUniversity.model.dto.entity.teacher_discipline.ITeacherShortDisciplinesDTO;
import com.website.eUniversity.model.entity.TeacherDiscipline;
import com.website.eUniversity.repository.IDisciplineRepository;
import com.website.eUniversity.repository.ITeacherDisciplineRepository;
import com.website.eUniversity.repository.ITeacherRepository;
import com.website.eUniversity.service.ITeacherDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeacherDisciplineService implements ITeacherDisciplineService {

    @Autowired
    ITeacherDisciplineRepository teacherDisciplineRepository;

    @Autowired
    ITeacherRepository teacherRepository;

    @Autowired
    IDisciplineRepository disciplineRepository;

    @Override
    public List<ITeacherShortDisciplinesDTO> getTeachersShortDisciplines() {
        return teacherDisciplineRepository.getTeachersShortDisciplines();
    }

    @Override
    public List<ITeacherDisciplineDTO> getTeacherDisciplines(Integer teacherId) {
        return teacherDisciplineRepository.getTeacherDisciplines(teacherId);
    }

    @Override
    public ITeacherShortDisciplinesDTO getTeacherShortDisciplines(Integer teacherId) {
        return teacherDisciplineRepository.getTeacherShortDisciplines(teacherId);
    }

    @Override
    public void attachDiscipline(AttachDisciplineDTO dto) {
        TeacherDiscipline teacherDiscipline = new TeacherDiscipline();
        teacherDiscipline.setTeacher(teacherRepository.findById(dto.getTeacherId()).get());
        teacherDiscipline.setDiscipline(disciplineRepository.findById(dto.getDisciplineId()).get());

        teacherDisciplineRepository.save(teacherDiscipline);
    }

    @Override
    @Transactional
    public void detachDiscipline(Integer teacherId, Integer disciplineId) {
        teacherDisciplineRepository.detachDiscipline(teacherId, disciplineId);
    }
}
