package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDisciplineRepository extends JpaRepository<Discipline, Integer> {

    @Query(value = "SELECT * FROM disciplines d " +
            "WHERE d.name LIKE %:search% OR d.short_name LIKE %:search% " +
            "ORDER BY d.id DESC " +
            "OFFSET (:pageIndex * :pageSize) " +
            "ROWS FETCH NEXT :pageSize " +
            "ROWS ONLY" , nativeQuery = true)
    List<Discipline> getPaginatedDisciplines(@Param("search") String search,
                                             @Param("pageIndex") Integer pageIndex,
                                             @Param("pageSize") Integer pageSize);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.DDLResponseDTO(d.id, d.shortName)" +
            "FROM Discipline d")
    List<DDLResponseDTO<Integer>> getDisciplineDDL();

    @Query(value = "SELECT COUNT(*) FROM disciplines d where d.name LIKE %:search% OR d.short_name LIKE %:search%",
    nativeQuery = true)
    Integer countAllByDiscipline_Name_ShortNameIsLike(@Param("search") String search);
}
