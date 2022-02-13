package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.GroupDiscipline;
import com.website.eUniversity.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMaterialRepository extends JpaRepository<Material, Integer> {

    @Query(value = "SELECT new com.website.eUniversity.model.entity" +
            ".Material(m.id, m.order, f, m.description, m.userId, m.groupDiscipline, m.educationalProcess) " +
            "FROM Material m INNER JOIN File f ON " +
            "f.id = m.file.id " +
            "LEFT JOIN StudentMaterial sm ON " +
            "m.id = sm.material.id WHERE sm.material.id is null AND m.groupDiscipline = :groupDiscipline " +
            "AND m.educationalProcess.name IN ('Seminar', 'Practise')")
    List<Material> findAllByGroupDiscipline(@Param("groupDiscipline") GroupDiscipline groupDiscipline);

    @Query(value = "SELECT new com.website.eUniversity.model.entity.Material" +
            "(m.id, m.order, f, m.description, m.userId, m.groupDiscipline, m.educationalProcess) " +
            "FROM Material m INNER JOIN StudentMaterial sm ON m.id = sm.material.id " +
            "INNER JOIN File f ON m.file.id = f.id " +
            "INNER JOIN GroupDiscipline gd on gd.id = m.groupDiscipline.id " +
            "WHERE sm.student.id = :studentId AND gd = :groupDiscipline")
    List<Material> findAllStudentMaterials(@Param("studentId") Integer studentId, @Param("groupDiscipline") GroupDiscipline groupDiscipline);
}