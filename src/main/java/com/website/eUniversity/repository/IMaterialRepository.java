package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.GroupDiscipline;
import com.website.eUniversity.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMaterialRepository extends JpaRepository<Material, Integer> {

    @Query(value = "SELECT new com.website.eUniversity.model.entity.Material(m.id, m.order, m.description, m.userId, m.groupDiscipline, m.educationalProcess) " +
            "FROM Material m LEFT JOIN StudentMaterial sm ON " +
            "m.id = sm.id WHERE sm.id is null AND m.groupDiscipline = :groupDiscipline")
    List<Material> findAllByGroupDiscipline(@Param("groupDiscipline") GroupDiscipline groupDiscipline);

    @Query(value = "SELECT new com.website.eUniversity.model.entity.Material(m.id, m.order, m.description, m.userId, m.groupDiscipline, m.educationalProcess) " +
            "FROM Material m INNER JOIN StudentMaterial sm ON m.id = sm.material.id " +
            "INNER JOIN GroupDiscipline gd on gd = :groupDiscipline " +
            "WHERE sm.student.id = :studentId")
    List<Material> findAllStudentMaterials(@Param("studentId") Integer studentId, @Param("groupDiscipline") GroupDiscipline groupDiscipline);
}
