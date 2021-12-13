package com.website.eUniversity.util;

import com.website.eUniversity.exception.TokenExpiredException;
import com.website.eUniversity.exception.TokenNotFoundException;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.RefreshToken;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.IRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Component
public class RefreshTokenUtil {

    @Autowired
    private IRefreshTokenRepository refreshTokenRepository;

    @Autowired
    private IAccountRepository accountRepository;

    public static final long REFRESH_TOKEN_VALIDITY = 24 * 3600;

    @Transactional
    public String generateRefreshToken(UserDetails userDetails) {
        RefreshToken refreshToken = new RefreshToken();
        Optional<Account> account = accountRepository.findAccountByLogin(userDetails.getUsername());

        if(!account.isPresent()) {
            throw new UsernameNotFoundException("Account not found");
        }

        refreshToken.setAccount(account.get());
        refreshToken.setCreatedAt(new Date());
        refreshToken.setExpiredAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY * 1000));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);

        return refreshToken.getToken();
    }

    public RefreshToken validateRefreshToken(String token) throws TokenNotFoundException, TokenExpiredException {
        Date now = new Date();
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);

        if(!refreshToken.isPresent())
            throw new TokenNotFoundException();

        if(refreshToken.get().isExpired() || refreshToken.get().getExpiredAt().compareTo(new Date()) > 0)
            throw new TokenExpiredException();

        return refreshToken.get();
    }

    public boolean revokeRefreshToken(String token) throws TokenNotFoundException {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);

        if(!refreshToken.isPresent())
            throw new TokenNotFoundException();

        refreshToken.get().setExpired(true);

        refreshTokenRepository.save(refreshToken.get());
        return true;
    }
}
