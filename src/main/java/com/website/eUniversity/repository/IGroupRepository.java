package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.StudentShortInfoDTO;
import com.website.eUniversity.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGroupRepository extends JpaRepository<Group, Integer> {

    @Query(value = "SELECT * FROM groups g " +
            "WHERE g.name LIKE %:search% " +
            "ORDER BY g.id DESC " +
            "OFFSET (:pageIndex * :pageSize) " +
            "ROWS FETCH NEXT :pageSize " +
            "ROWS ONLY", nativeQuery = true)
    List<Group> getPaginatedGroups(@Param("search") String search,
                                   @Param("pageIndex") Integer pageIndex,
                                   @Param("pageSize") Integer pageSize);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.admin_panel.entity.StudentShortInfoDTO" +
            " (s.id, a.fullName)" +
            " FROM Student s INNER JOIN Account a on s.account = a " +
            "                INNER JOIN Group g on s.group = g" +
            " WHERE g.id = :id")
    List<StudentShortInfoDTO> findAllStudents(@Param("id") Integer groupId);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.DDLResponseDTO(g.id, g.name)" +
                   "FROM Group g")
    List<DDLResponseDTO<Integer>> getAllGroupsDDL();

    @Query(value = "SELECT new com.website.eUniversity.model.dto.DDLResponseDTO(s.id, s.account.fullName)" +
            " FROM Student s LEFT OUTER JOIN Group g ON g.id = s.group.id" +
            " WHERE s.group.id is null")
    List<DDLResponseDTO<Integer>> findStudentsWithoutGroup();

    @Query(value = "SELECT new com.website.eUniversity.model.dto.DDLResponseDTO(s.id, s.account.fullName)" +
            " FROM Student s INNER JOIN Group g ON g = s.group" +
            " WHERE g.id = :id")
    List<DDLResponseDTO<Integer>> findStudentsOfGroup(@Param("id") Integer groupId);

    @Query(value = "SELECT COUNT(*) FROM groups g where g.name LIKE %:search%",
            nativeQuery = true)
    Integer countAllByGroups_Name_IsLike(@Param("search") String search);
}
