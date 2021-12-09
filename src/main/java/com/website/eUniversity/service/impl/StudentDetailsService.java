package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.repository.IStudentRepository;
import com.website.eUniversity.service.IStudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailsService implements IStudentDetailsService {
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findStudentByLogin(username);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("STUDENT"));

        return new User(student.getLogin(), student.getPassword(), grantedAuthorities);
    }
}
