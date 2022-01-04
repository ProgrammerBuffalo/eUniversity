package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.TeacherDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {

    void deleteById(Integer id);

    void deleteByAccount_Id(String id);

    Optional<Teacher> findByAccount(Account account);

    Optional<Teacher> findByAccount_Id(String id);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.TeacherDTO(a.id, a.fullName, a.age, a.login, a.password)" +
            " FROM Teacher t INNER JOIN Account a on t.account.id = a.id")
    List<TeacherDTO> findAllTeachers();
}
