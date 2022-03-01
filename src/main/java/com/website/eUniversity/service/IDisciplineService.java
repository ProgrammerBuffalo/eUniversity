package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.PaginationDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.discipline.AddDisciplineDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.discipline.UpdateDisciplineDTO;
import com.website.eUniversity.model.entity.Discipline;

import java.util.List;

public interface IDisciplineService {
    Integer save(AddDisciplineDTO disciplineDTO);

    PaginatedListDTO<Discipline> getAllDisciplines(PaginationDTO dto);

    List<DDLResponseDTO<Integer>> getDisciplinesDDL();

    void update(UpdateDisciplineDTO disciplineDTO);

    void delete(Integer id);
}
