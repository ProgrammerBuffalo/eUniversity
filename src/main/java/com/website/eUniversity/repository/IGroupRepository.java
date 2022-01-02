package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.StudentShortInfoDTO;
import com.website.eUniversity.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGroupRepository extends JpaRepository<Group, Integer> {

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.StudentShortInfoDTO" +
            " (s.fullName)" +
            " FROM Student s INNER JOIN Group g on s.group = g" +
            " WHERE g.id = :id")
    List<StudentShortInfoDTO> findAllStudents(@Param("id") Integer groupId);

}
