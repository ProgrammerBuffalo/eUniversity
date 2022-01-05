package com.website.eUniversity.repository;

import com.website.eUniversity.model.dto.identification.AuthorizationResponseDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findAccountByLogin(String login);

    Optional<Account> findAccountByLoginAndPassword(String login, String password);

    Optional<Account> findAccountById(String id);

    void deleteById(String id);
}
