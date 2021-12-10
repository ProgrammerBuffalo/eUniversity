package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.Role;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.IStudentRepository;
import com.website.eUniversity.repository.ITeacherRepository;
import com.website.eUniversity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class TeacherService implements IUserService {
    @Autowired
    private ITeacherRepository teacherRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    @Transactional
    public void register(RegistrationDTO registrationDTO) {
        try {
            Account account = accountRepository.save(new Account(registrationDTO.getLogin(), registrationDTO.getPassword(), Role.TEACHER));
            teacherRepository.save(new Student(registrationDTO.getFullName(), account));
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void delete(String uuid) {
        try {
            teacherRepository.deleteById(uuid);
        }
        catch (Exception ex) {
            throw ex;
        }
    }
}
