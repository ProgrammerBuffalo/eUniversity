package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.DDLResponseDTO;
import com.website.eUniversity.model.dto.entity.StudentShortInfoDTO;
import com.website.eUniversity.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGroupRepository extends JpaRepository<Group, Integer> {

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.StudentShortInfoDTO" +
            " (s.id, a.fullName)" +
            " FROM Student s INNER JOIN Account a on s.account = a " +
            "                INNER JOIN Group g on s.group = g" +
            " WHERE g.id = :id")
    List<StudentShortInfoDTO> findAllStudents(@Param("id") Integer groupId);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.DDLResponseDTO(g.id, g.name)" +
                   "FROM Group g")
    List<DDLResponseDTO<Integer>> getAllGroupsDDL();

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.DDLResponseDTO(s.id, s.account.fullName)" +
            " FROM Student s LEFT OUTER JOIN Group g ON g.id = s.group.id" +
            " WHERE s.group.id is null")
    List<DDLResponseDTO<Integer>> findStudentsWithoutGroup();
}
