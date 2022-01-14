package com.website.eUniversity.service.func;

import com.website.eUniversity.model.dto.entity.base.UserDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class AccountSaver <T extends UserDTO> {

    @Autowired
    private IAccountRepository accountRepository;

    protected Account saveAccount(T user) {
        Optional<Account> account = accountRepository.findAccountById(user.getAccountId());

        if(!account.isPresent()){
            throw new UsernameNotFoundException("Account not found");
        }

        account.get().setFullName(user.getFullName());
        account.get().setAge(user.getAge());
        account.get().setLogin(user.getLogin());

        return accountRepository.save(account.get());
    }
}
