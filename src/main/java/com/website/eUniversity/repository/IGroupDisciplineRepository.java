package com.website.eUniversity.repository;

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

    Optional<GroupDiscipline> findByGroupAndDiscipline(Group group, Discipline discipline);

    @Query(value = "SELECT gd.id, g.id as group_id, d.id as discipline_id, t.id as teacher_id " +
            "       FROM Groups_Disciplines gd INNER JOIN Groups g on gd.group_id = g.id" +
            "                                  INNER JOIN Teachers t on gd.teacher_id = t.id" +
            "                                  INNER JOIN Accounts a on t.account_id = a.id" +
            "                                  INNER JOIN Disciplines d on gd.discipline_id = d.id" +
            "       WHERE g.id = :id", nativeQuery = true)
    List<GroupDiscipline> findByGroupIdTeachersAndDisciplines(@Param("id") Integer groupId);

}
