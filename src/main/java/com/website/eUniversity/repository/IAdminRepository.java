package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.AdminDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Admin;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

    void deleteByAccount_Id(String id);

    Optional<Admin> findById(Integer id);

    Optional<Admin> findByAccount(Account account);

    Optional<Admin> findByAccount_Id(String id);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.AdminDTO(acc.id, ad.id, acc.fullName, acc.age, acc.login, acc.password)" +
            " FROM Admin ad INNER JOIN Account acc on ad.account.id = acc.id")
    List<AdminDTO> findAllAdmins();
}
