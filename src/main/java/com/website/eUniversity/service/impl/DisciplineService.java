package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.discipline.AddDisciplineDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.discipline.UpdateDisciplineDTO;
import com.website.eUniversity.model.entity.Discipline;
import com.website.eUniversity.repository.IDisciplineRepository;
import com.website.eUniversity.service.IDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
public class DisciplineService implements IDisciplineService {

    @Autowired
    private IDisciplineRepository disciplineRepository;

    @Override
    public Integer save(AddDisciplineDTO disciplineDTO) {
        Discipline discipline = new Discipline();
        discipline.setName(disciplineDTO.getName());
        discipline.setShortName(disciplineDTO.getShortName());

        disciplineRepository.save(discipline);
        return discipline.getId();
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.getAll();
    }

    @Override
    public List<DDLResponseDTO<Integer>> getDisciplinesDDL() {
        return disciplineRepository.getDisciplineDDL();
    }

    @Override
    @Transactional
    public void update(UpdateDisciplineDTO disciplineDTO) {
        Discipline discipline = disciplineRepository.findById(disciplineDTO.getId()).get();

        discipline.setName(disciplineDTO.getName());
        discipline.setShortName(disciplineDTO.getShortName());

        disciplineRepository.save(discipline);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        disciplineRepository.deleteById(id);
    }
}
