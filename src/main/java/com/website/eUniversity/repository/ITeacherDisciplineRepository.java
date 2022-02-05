package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.IDDLResponseDTO;
import com.website.eUniversity.model.dto.entity.teacher_discipline.ITeacherDisciplineDTO;
import com.website.eUniversity.model.dto.entity.teacher_discipline.ITeacherShortDisciplinesDTO;
import com.website.eUniversity.model.entity.TeacherDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITeacherDisciplineRepository extends JpaRepository<TeacherDiscipline, Integer> {

    @Query(value = "SELECT t.id, a.full_name AS name, STRING_AGG(d.short_name, ', ') AS shortDisciplines" +
            " FROM teachers t" +
            " LEFT JOIN teacher_discipline td ON td.teacher_id = t.id" +
            " LEFT JOIN disciplines d ON d.id = td.discipline_id" +
            " INNER JOIN accounts a ON a.id = t.account_id" +
            " GROUP BY t.id, a.full_name", nativeQuery = true)
    List<ITeacherShortDisciplinesDTO> getTeachersShortDisciplines();

    @Query(value = "SELECT td.discipline_id AS id, d.[name]" +
            " FROM teacher_discipline as td" +
            " INNER JOIN disciplines d ON d.id = td.discipline_id" +
            " WHERE td.teacher_id = :id", nativeQuery = true)
    List<ITeacherDisciplineDTO> getTeacherDisciplines(@Param("id") Integer teacherId);

//    List<TeacherDiscipline> getTeacherDisciplinesByTeacher_Id(Integer teacherId);

    @Query(value = "SELECT td.teacher_id AS id, a.full_name AS name" +
            " FROM teacher_discipline as td" +
            " INNER JOIN teachers t ON t.id = td.teacher_id" +
            " INNER JOIN accounts a on a.id = t.account_id" +
            " WHERE td.discipline_id = :disciplineId", nativeQuery = true)
    List<IDDLResponseDTO<Integer>> getDisciplineTeachers(@Param("disciplineId") Integer disciplineId);


    @Query(value = "SELECT td.teacher_id AS id, a.full_name AS [name], STRING_AGG(d.short_name, ', ') AS shortDisciplines" +
            " FROM teacher_discipline td" +
            " INNER JOIN teachers t ON t.id = td.teacher_id" +
            " INNER JOIN disciplines d ON d.id = td.discipline_id" +
            " INNER JOIN accounts a ON a.id = t.account_id" +
            " WHERE td.teacher_id = :id" +
            " GROUP BY td.teacher_id, a.full_name", nativeQuery = true)
    ITeacherShortDisciplinesDTO getTeacherShortDisciplines(@Param("id") Integer teacherId);

    @Modifying
    @Query(value = "DELETE FROM teacher_discipline WHERE teacher_id = :teacherId AND discipline_id = :disciplineId", nativeQuery = true)
    void detachDiscipline(@Param("teacherId") Integer teacherId, @Param("disciplineId") Integer disciplineId);
}
