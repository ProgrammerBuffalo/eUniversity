package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findAccountByLogin(String login);
}
