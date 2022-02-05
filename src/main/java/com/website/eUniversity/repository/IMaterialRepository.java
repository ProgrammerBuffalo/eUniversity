package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.GroupDiscipline;
import com.website.eUniversity.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMaterialRepository extends JpaRepository<Material, Integer> {

    List<Material> findAllByGroupDiscipline(GroupDiscipline groupDiscipline);

    @Query(value = "SELECT new com.website.eUniversity.model.entity.Material() " +
            "FROM Material m INNER JOIN StudentMaterial sm ON m.id = sm.material.id " +
            "INNER JOIN GroupDiscipline gd on gd = :groupDiscipline " +
            "WHERE sm.student.id = :studentId")
    List<Material> findAllStudentMaterials(@Param("studentId") Integer studentId, @Param("groupDiscipline") GroupDiscipline groupDiscipline);
}
