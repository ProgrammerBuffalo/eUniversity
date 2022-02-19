package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.PaginationDTO;
import com.website.eUniversity.model.dto.entity.DisciplineDTO;
import com.website.eUniversity.model.dto.entity.discipline.AddDisciplineDTO;
import com.website.eUniversity.model.dto.entity.discipline.UpdateDisciplineDTO;
import com.website.eUniversity.model.entity.Discipline;
import com.website.eUniversity.repository.IDisciplineRepository;
import com.website.eUniversity.service.IDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public PaginatedListDTO getAllDisciplines(PaginationDTO dto) {
        return new PaginatedListDTO<DisciplineDTO>().setItems(disciplineRepository
                .getPaginatedDisciplines(dto.getSearch(), dto.getPageIndex(), dto.getPageSize())
                .stream()
                .map(Discipline::toDTO)
                .collect(Collectors.toList()))
                .setAllItemsCount(disciplineRepository.countAllByDiscipline_Name_ShortNameIsLike(dto.getSearch()));
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
