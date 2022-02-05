package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.entity.discipline.AddDisciplineDTO;
import com.website.eUniversity.model.dto.entity.discipline.UpdateDisciplineDTO;
import com.website.eUniversity.model.entity.Discipline;

import java.util.List;

public interface IDisciplineService {
    Integer save(AddDisciplineDTO disciplineDTO);

    List<Discipline> getAllDisciplines();

    List<DDLResponseDTO<Integer>> getDisciplinesDDL();

    void update(UpdateDisciplineDTO disciplineDTO);

    void delete(Integer id);
}
