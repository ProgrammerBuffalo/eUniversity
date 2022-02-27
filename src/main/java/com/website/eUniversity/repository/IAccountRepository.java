package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findAccountByLogin(String login);

    Optional<Account> findAccountByLoginAndPassword(String login, String password);

    Optional<Account> findAccountById(String id);

    void deleteById(String id);
}
