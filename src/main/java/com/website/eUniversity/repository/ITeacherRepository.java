package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.TeacherDTO;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITeacherRepository extends JpaRepository<Teacher, String> {

    void deleteById(String id);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.TeacherDTO(t.fullName, a.login, a.password)" +
            " FROM Teacher t INNER JOIN Account a on t.account.id = a.id")
    List<TeacherDTO> findAllTeachers();
}
