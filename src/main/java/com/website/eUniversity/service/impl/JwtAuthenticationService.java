package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.exception.RefreshTokenExpiredException;
import com.website.eUniversity.exception.RefreshTokenNotFoundException;
import com.website.eUniversity.model.dto.identification.AuthorizationRequestDTO;
import com.website.eUniversity.model.dto.identification.AuthorizationResponseDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.RefreshToken;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.IRefreshTokenRepository;
import com.website.eUniversity.service.base.IAccountDetailsService;
import com.website.eUniversity.service.IAuthenticationService;
import com.website.eUniversity.util.JwtTokenUtil;
import com.website.eUniversity.util.RefreshTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class JwtAuthenticationService implements IAuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RefreshTokenUtil refreshTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAccountDetailsService accountDetailsService;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRefreshTokenRepository refreshTokenRepository;

    @Override
    public AuthorizationResponseDTO authorize(AuthorizationRequestDTO authorizationDTO) throws NotFoundException {
        Authentication authentication = new
                UsernamePasswordAuthenticationToken(authorizationDTO.getLogin(), authorizationDTO.getPassword());

        Optional<Account> account = accountRepository.findAccountByLoginAndPassword(authorizationDTO.getLogin(), authorizationDTO.getPassword());

        if(!account.isPresent()) {
            throw new NotFoundException("Account not found");
        }

        authentication = authenticationManager.authenticate(authentication);

        String jwt = jwtTokenUtil.generateToken((UserDetails)authentication.getPrincipal());
        String rt = refreshTokenUtil.generateRefreshToken((UserDetails)authentication.getPrincipal());

        return new AuthorizationResponseDTO(account.get().getId(), account.get().getFullName(), account.get().getRole(), jwt, rt);
    }

    @Override
    @Transactional
    public AuthorizationResponseDTO refreshToken(String refreshToken) throws RefreshTokenExpiredException, RefreshTokenNotFoundException {
        RefreshToken rt = refreshTokenUtil.validateRefreshToken(refreshToken);

        rt.setExpired(true);
        rt = refreshTokenRepository.save(rt);

        UserDetails userDetails = accountDetailsService.loadUserByUsername(rt.getAccount().getLogin());

        String newJwt = jwtTokenUtil.generateToken(userDetails);
        String newRt = refreshTokenUtil.generateRefreshToken(userDetails);

        return new AuthorizationResponseDTO(rt.getAccount().getId(), rt.getAccount().getFullName(), rt.getAccount().getRole(), newJwt, newRt);
    }

}
