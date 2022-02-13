package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.StudentDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Student;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student, Integer> {

    void deleteById(Integer id);

    void deleteByAccount_Id(String id);

    Optional<Student> findById(Integer id);

    Optional<Student> findByAccount(Account account);

    Optional<Student> findByAccount_Id(String id);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.StudentDTO(a.id, s.id, a.fullName, a.age, a.login, a.password, g.name)" +
            " FROM Student s INNER JOIN Account a on s.account.id = a.id" +
            "                LEFT JOIN Group g on s.group = g" +
            "                WHERE a.fullName LIKE %:search% OR a.login LIKE %:search% OR g.name LIKE %:search%" +
            "                ORDER BY s.id DESC")
    List<StudentDTO> findAllStudents(@Param("search") String search);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.StudentDTO(s.account.id, s.id, s.account.fullName, s.account.age, s.account.login, s.account.password)" +
                   "FROM Student s WHERE s.group.id = ?1")
    List<StudentDTO> getAllByGroup(Integer groupId);

    @Query(value = "SELECT * FROM students " +
            "INNER JOIN accounts on students.account_id = accounts.id " +
            "WHERE accounts.full_name LIKE %:searchName% OR %:searchLogin% " +
            "ORDER BY students.id DESC " +
            "OFFSET (:pageIndex * :pageSize) " +
            "ROWS FETCH NEXT :pageSize " +
            "ROWS ONLY", nativeQuery = true)
    List<Student> getPaginatedStudents(@Param("searchName") String name,
                                       @Param("searchLogin") String login,
                                       @Param("pageIndex") Integer pageIndex,
                                       @Param("pageSize") Integer pageSize);

    @Query(value = "SELECT COUNT(*) FROM students s INNER JOIN accounts acc on acc.id = s.account_id where acc.full_name LIKE %:searchName% AND %:searchLogin%",
            nativeQuery = true)
    Integer countAllByAccount_FullNameIsLike(@Param("searchName") String name, @Param("searchLogin") String login);

}
