package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.DDLResponseDTO;
import com.website.eUniversity.model.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDisciplineRepository extends JpaRepository<Discipline, Integer> {

    @Query(value = "SELECT new com.website.eUniversity.model.entity.Discipline(d.id, d.name, d.shortName)" +
            "FROM Discipline d")
    List<Discipline> getAll();

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.DDLResponseDTO(d.id, d.shortName)" +
            "FROM Discipline d")
    List<DDLResponseDTO<Integer>> getDisciplineDDL();
}
