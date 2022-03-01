package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMaterialRepository extends JpaRepository<Material, Integer> {

    @Query(value = "SELECT * FROM materials m " +
            "INNER JOIN accounts a ON a.id = m.user_id " +
            "INNER JOIN educational_processes ep ON ep.id = m.educational_process_id " +
            "INNER JOIN files f ON f.id = m.file_id " +
            "WHERE m.group_discipline_id = :groupDisciplineId " +
            "AND (a.full_name LIKE %:search% OR f.original_file_name LIKE %:search% OR ep.name LIKE %:search%) " +
            "ORDER BY m.id DESC " +
            "OFFSET (:pageIndex * :pageSize) " +
            "ROWS FETCH NEXT :pageSize " +
            "ROWS ONLY", nativeQuery = true)
    List<Material> getEducationPaginatedList(@Param("groupDisciplineId") Integer groupDisciplineId,
                                             @Param("search") String search,
                                             @Param("pageIndex") Integer pageIndex,
                                             @Param("pageSize") Integer pageSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM materials m " +
            "INNER JOIN accounts a ON a.id = m.user_id " +
            "INNER JOIN files f ON f.id = m.file_id " +
            "INNER JOIN educational_processes ep ON ep.id = m.educational_process_id " +
            "WHERE m.group_discipline_id = :groupDisciplineId " +
            "AND (a.full_name LIKE %:search% OR f.original_file_name LIKE %:search% OR ep.name LIKE %:search%)", nativeQuery = true)
    Integer countAllEducationBy_FileName_AccountFullName_EcuProcess_IsLike(@Param("groupDisciplineId") Integer groupDisciplineId, @Param("search") String search);

    @Query(value = "SELECT * FROM materials m " +
            "INNER JOIN students_materials sm on sm.material_id = m.id " +
            "INNER JOIN accounts a ON a.id = m.user_id " +
            "INNER JOIN files f ON f.id = m.file_id " +
            "INNER JOIN educational_processes ep ON ep.id = m.educational_process_id " +
            "WHERE m.group_discipline_id = :groupDisciplineId " +
            "AND a.id = :studentId " +
            "AND ep.id IN (2, 3)" +
            "AND (a.full_name LIKE %:search% OR f.original_file_name LIKE %:search% OR ep.name LIKE %:search%)" +
            "ORDER BY m.id DESC " +
            "OFFSET (:pageIndex * :pageSize) " +
            "ROWS FETCH NEXT :pageSize " +
            "ROWS ONLY", nativeQuery = true)
    List<Material> getStudentPaginatedList(@Param("groupDisciplineId") Integer groupDisciplineId,
                                           @Param("studentId") Integer studentId,
                                           @Param("search") String search,
                                           @Param("pageIndex") Integer pageIndex,
                                           @Param("pageSize") Integer pageSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM materials " +
            "INNER JOIN students_materials sm on sm.material_id = m.id " +
            "INNER JOIN accounts a ON a.id = m.user_id " +
            "INNER JOIN files f ON f.id = m.file_id " +
            "INNER JOIN educational_processes ep ON ep.id = m.educational_process_id " +
            "WHERE m.group_discipline_id = :groupDisciplineId " +
            "AND a.id = :studentId " +
            "AND ep.id IN (2, 3)" +
            "AND (a.full_name LIKE %:search% OR f.original_file_name LIKE %:search% OR ep.name LIKE %:search%)", nativeQuery = true)
    Integer countAllStudentBy_FileName_AccountName_EcuProcess_IsLike(@Param("groupDisciplineId") Integer groupDisciplineId,
                                                                     @Param("studentId") Integer studentId,
                                                                     @Param("search") String search);

}