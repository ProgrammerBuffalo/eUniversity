package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.StudentDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student, Integer> {

    void deleteById(Integer id);

    void deleteByAccount_Id(String id);

    Optional<Student> findById(String id);

    Optional<Student> findByAccount(Account account);

    Optional<Student> findByAccount_Id(String id);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.StudentDTO(a.id, a.fullName, a.age, a.login, a.password)" +
            " FROM Student s INNER JOIN Account a on s.account.id = a.id" +
            "                INNER JOIN Group g on s.group = g")
    List<StudentDTO> findAllStudents();

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.StudentDTO(s.account.id, s.account.fullName, s.account.age, s.account.login, s.account.password)" +
                   "FROM Student s WHERE s.group.id = ?1")
    List<StudentDTO> getAllByGroup(Integer groupId);
}
