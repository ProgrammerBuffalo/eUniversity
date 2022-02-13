package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.TeacherDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Admin;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.model.entity.Teacher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {

    void deleteById(Integer id);

    void deleteByAccount_Id(String id);

    Optional<Teacher> findById(Integer id);

    Optional<Teacher> findByAccount_Id(String id);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.TeacherDTO(a.id, t.id, a.fullName, a.age, a.login, a.password)" +
            " FROM Teacher t" +
            " INNER JOIN Account a on t.account.id = a.id" +
            " WHERE a.fullName LIKE %:search% OR a.login LIKE %:search%" +
            " ORDER BY a.id DESC")
    List<TeacherDTO> findAllTeachers(@Param("search") String search);

    @Query(value = "SELECT * FROM teachers " +
            "INNER JOIN accounts on teachers.account_id = accounts.id " +
            "WHERE accounts.full_name LIKE %:search% OR %:login% " +
            "ORDER BY teachers.id DESC " +
            "OFFSET (:pageIndex * :pageSize) " +
            "ROWS FETCH NEXT :pageSize " +
            "ROWS ONLY", nativeQuery = true)
    List<Teacher> getPaginatedTeachers(@Param("searchName") String name,
                                       @Param("searchLogin") String login,
                                       @Param("pageIndex") Integer pageIndex,
                                       @Param("pageSize") Integer pageSize);

    @Query(value = "SELECT COUNT(*) FROM teachers t INNER JOIN accounts acc on acc.id = t.account_id where acc.full_name LIKE %:search%",
            nativeQuery = true)
    Integer countAllByAccount_FullNameIsLike(@Param("search") String search);
}
