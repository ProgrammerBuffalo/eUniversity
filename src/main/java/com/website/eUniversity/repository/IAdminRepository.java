package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.entity.AdminDTO;
import com.website.eUniversity.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAdminRepository extends JpaRepository<Admin, String> {
    @Query(value = "SELECT new com.website.eUniversity.model.dto.entity.AdminDTO(acc.id, acc.fullName, acc.age, acc.login, acc.password)" +
            " FROM Admin ad INNER JOIN Account acc on ad.account.id = acc.id")
    List<AdminDTO> findAllAdmins();
}