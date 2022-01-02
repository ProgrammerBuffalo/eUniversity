package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.StudentDTO;
import com.website.eUniversity.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student, String> {

    void deleteById(String id);

    Optional<Student> findById(String id);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.StudentDTO(s.id, s.fullName, a.login, a.password)" +
            " FROM Student s INNER JOIN Account a on s.account.id = a.id")
    List<StudentDTO> findAllStudents();
}
