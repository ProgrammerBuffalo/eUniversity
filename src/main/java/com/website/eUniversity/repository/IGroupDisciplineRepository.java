package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.GroupDisciplineTeacherDTO;
import com.website.eUniversity.model.entity.Discipline;
import com.website.eUniversity.model.entity.Group;
import com.website.eUniversity.model.entity.GroupDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IGroupDisciplineRepository extends JpaRepository<GroupDiscipline, Integer> {

    Optional<GroupDiscipline> findByGroupAndDiscipline(Group group, Discipline discipline);

    @Query(value = "SELECT g.name as groupName, d.name as disciplineName, t.full_name as teacherName " +
            "      FROM Groups_Disciplines INNER JOIN Groups g on Groups_Disciplines.group_id = g.id" +
            "                              INNER JOIN Teachers t on Groups_Disciplines.teacher_id = t.id" +
            "                              INNER JOIN Disciplines d on Groups_Disciplines.discipline_id = d.id;",
            nativeQuery = true)
    List<GroupDisciplineTeacherDTO> findAllGroupsWithDisciplines();

}
