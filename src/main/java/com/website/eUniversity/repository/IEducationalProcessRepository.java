package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.DDLResponseDTO;
import com.website.eUniversity.model.entity.EducationalProcess;
import com.website.eUniversity.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEducationalProcessRepository extends JpaRepository<EducationalProcess, Integer> {

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.DDLResponseDTO(e.id, e.name) FROM EducationalProcess e WHERE e.name IN ('Lecture', 'Seminar', 'Practise')")
    List<DDLResponseDTO<Integer>> findLessons();

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.DDLResponseDTO(e.id, e.name) FROM EducationalProcess e WHERE e.name IN ('Exam', 'Midterm')")
    List<DDLResponseDTO<Integer>> findExams();

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.DDLResponseDTO(e.id, e.name) FROM EducationalProcess e")
    List<DDLResponseDTO<Integer>> getAll();
}
