package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByLogin(String login);
}
