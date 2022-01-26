package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.DDLResponseDTO;
import com.website.eUniversity.model.dto.entity.GroupDisciplineResponseDTO;
import com.website.eUniversity.model.entity.Discipline;
import com.website.eUniversity.model.entity.Group;
import com.website.eUniversity.model.entity.GroupDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IGroupDisciplineRepository extends JpaRepository<GroupDiscipline, Integer> {

    @Query(value = "SELECT gd.id, g.id as group_id, d.id as discipline_id, t.id as teacher_id " +
            "       FROM Groups_Disciplines gd INNER JOIN Groups g on gd.group_id = g.id" +
            "                                  INNER JOIN Teachers t on gd.teacher_id = t.id" +
            "                                  INNER JOIN Accounts a on t.account_id = a.id" +
            "                                  INNER JOIN Disciplines d on gd.discipline_id = d.id" +
            "       WHERE g.id = :id", nativeQuery = true)
    List<GroupDiscipline> findByGroupIdTeachersAndDisciplines(@Param("id") Integer groupId);

    Optional<GroupDiscipline> findById(Integer id);

    Optional<GroupDiscipline> findByGroup_IdAndDiscipline_IdAndTeacher_Id(Integer groupId, Integer disciplineId, Integer teacherId);

    Optional<GroupDiscipline> findByGroup_Id(Integer id);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.DDLResponseDTO(gd.teacher.id, gd.teacher.account.fullName) FROM GroupDiscipline gd" +
            " WHERE gd.group.id = :groupId AND gd.discipline.id = :disciplineId")
    List<DDLResponseDTO<Integer>> findTeachersOfDisciplineAndGroup(@Param("groupId") Integer groupId, @Param("disciplineId") Integer disciplineId);

    @Query(value = "SELECT DISTINCT new com.website.eUniversity.model.dto.entity.DDLResponseDTO(d.id, d.name) FROM GroupDiscipline gd" +
            " INNER JOIN Discipline d ON d.id = gd.discipline.id" +
            " WHERE gd.group.id = :groupId")
    List<DDLResponseDTO<Integer>> getGroupDisciplinesDDL(@Param("groupId") Integer groupId);
}
