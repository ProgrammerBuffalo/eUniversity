package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.admin_panel.entity.AdminDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

    void deleteByAccount_Id(String id);

    Optional<Admin> findById(Integer id);

    Optional<Admin> findByAccount(Account account);

    Optional<Admin> findByAccount_Id(String id);

    @Query(value = "SELECT new com.website.eUniversity.model.dto.admin_panel.entity.AdminDTO(acc.id, ad.id, acc.fullName, acc.age, acc.login, acc.password)" +
            " FROM Admin ad INNER JOIN Account acc on ad.account.id = acc.id" +
            " WHERE acc.fullName LIKE %:search% OR acc.login LIKE %:search%" +
            " ORDER BY ad.id DESC")
    List<AdminDTO> findAllAdmins(@Param("search") String search);

    @Query(value = "SELECT * FROM admins " +
            "INNER JOIN accounts on admins.account_id = accounts.id " +
            "WHERE accounts.full_name LIKE %:search% OR accounts.login LIKE %:search% " +
            "ORDER BY admins.id DESC " +
            "OFFSET (:pageIndex * :pageSize) " +
            "ROWS FETCH NEXT :pageSize " +
            "ROWS ONLY", nativeQuery = true)
    List<Admin> getPaginatedAdmins(@Param("search") String search,
                                   @Param("pageIndex") Integer pageIndex,
                                   @Param("pageSize") Integer pageSize);

    @Query(value = "SELECT COUNT(*) FROM admins a INNER JOIN accounts acc on acc.id = a.account_id where acc.full_name LIKE %:search% OR acc.login LIKE %:search%",
            nativeQuery = true)
    Integer countAllByAccount_FullNameIsLike(@Param("search") String search);
}
