package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.RefreshTokenExpiredException;
import com.website.eUniversity.exception.RefreshTokenNotFoundException;
import com.website.eUniversity.model.dto.identification.AuthorizationDTO;
import com.website.eUniversity.model.dto.identification.TokensDTO;
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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public TokensDTO authorize(AuthorizationDTO authorizationDTO) {
        Authentication authentication = new
                UsernamePasswordAuthenticationToken(authorizationDTO.getLogin(), authorizationDTO.getPassword());

        authenticationManager.authenticate(authentication);

        UserDetails userDetails = accountDetailsService.loadUserByUsername(authentication.getPrincipal().toString());

        String jwt = jwtTokenUtil.generateToken(userDetails);
        String rt = refreshTokenUtil.generateRefreshToken(userDetails);

        return new TokensDTO(jwt, rt);
    }

    @Override
    @Transactional
    public TokensDTO refreshToken(String refreshToken) throws RefreshTokenExpiredException, RefreshTokenNotFoundException {
        RefreshToken rt = refreshTokenUtil.validateRefreshToken(refreshToken);

        rt.setExpired(true);
        rt = refreshTokenRepository.save(rt);

        UserDetails userDetails = accountDetailsService.loadUserByUsername(rt.getAccount().getLogin());

        String newJwt = jwtTokenUtil.generateToken(userDetails);
        String newRt = refreshTokenUtil.generateRefreshToken(userDetails);

        return new TokensDTO(newJwt, newRt);
    }

}
