package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.GroupDiscipline;
import com.website.eUniversity.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findAllByGroupDiscipline_Group_Id(Integer groupId);

    Optional<Schedule> findByGroupDiscipline(GroupDiscipline groupDiscipline);

    @Query(value = "SELECT s FROM Schedule s " +
            "INNER JOIN EducationalProcess e ON s.educationalProcess = e " +
            "WHERE e.name IN ('Lecture', 'Seminar', 'Practise') AND s.groupDiscipline.group.id = :groupId")
    List<Schedule> findAllLessonsByGroupId(@Param("groupId") Integer groupId);

    @Query(value = "SELECT s FROM Schedule s INNER JOIN EducationalProcess e ON s.educationalProcess = e" +
            " WHERE e.name IN ('Exam', 'Midterm') AND s.groupDiscipline.group.id = :groupId")
    List<Schedule> findAllExamsByGroupId(@Param("groupId") Integer groupId);
}
