package com.website.eUniversity.filter;

import com.website.eUniversity.service.base.IAccountDetailsService;
import com.website.eUniversity.service.IFilterExceptionHandler;
import com.website.eUniversity.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private IAccountDetailsService accountDetailsService;

    @Autowired
    private IFilterExceptionHandler filterExceptionHandler;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private Set<String> skipUrls = new HashSet<>();
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    public JwtRequestFilter() {
        skipUrls.add("/authentication/authorize");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        String jwt = null;
        String userLogin = null;

        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                userLogin = jwtTokenUtil.getLoginFromToken(jwt);
            }

            if (userLogin != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.accountDetailsService.loadUserByUsername(userLogin);

                if (jwtTokenUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        catch (BadCredentialsException ex) {
            filterExceptionHandler.handleException(response, HttpStatus.FORBIDDEN, ex);
            return;
        }
        catch (JwtException ex) {
            filterExceptionHandler.handleException(response, HttpStatus.UNAUTHORIZED, ex);
            return;
        }
        catch (Exception ex) {
            filterExceptionHandler.handleException(response, HttpStatus.BAD_REQUEST, ex);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return skipUrls.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
    }

}
