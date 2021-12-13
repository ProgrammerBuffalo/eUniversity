package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.service.IAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountDetailsService implements IAccountDetailsService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findAccountByLogin(username);

        if(!account.isPresent()) {
            throw new UsernameNotFoundException("Account not found");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(account.get().getRole().name()));

        return new User(account.get().getLogin(), account.get().getPassword(), grantedAuthorities);
    }
}
