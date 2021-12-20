package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.Role;
import com.website.eUniversity.model.dto.TeacherDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.model.entity.Teacher;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.ITeacherRepository;
import com.website.eUniversity.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    private ITeacherRepository teacherRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    @Transactional
    public TeacherDTO register(RegistrationDTO registrationDTO) {
        TeacherDTO teacherDTO = new TeacherDTO();
        try {
            Account account = accountRepository.save(new Account(registrationDTO.getLogin(), registrationDTO.getPassword(), Role.TEACHER));
            Teacher teacher = teacherRepository.save(new Teacher(registrationDTO.getFullName(), account));
            teacherDTO.setLogin(teacher.getAccount().getLogin());
            teacherDTO.setPassword(teacher.getAccount().getPassword());
            teacherDTO.setFullName(teacher.getFullName());
        }
        catch (Exception ex) {
            throw ex;
        }
        return teacherDTO;
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

    @Override
    public List<TeacherDTO> getTeacherList() {
        return teacherRepository.findAllTeachers();
    }
}
