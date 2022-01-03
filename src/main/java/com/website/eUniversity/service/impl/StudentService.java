package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.Role;
import com.website.eUniversity.model.dto.entity.StudentDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.IStudentRepository;
import com.website.eUniversity.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IAccountRepository accountRepository;

    // After mssql will be added
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public StudentDTO register(RegistrationDTO registrationDTO) {
        StudentDTO studentDTO = new StudentDTO();
        try {
            //String encryptedPassword = passwordEncoder.encode(registrationDTO.getPassword());

            Account account = accountRepository.save(new Account(registrationDTO.getLogin(), registrationDTO.getPassword(), registrationDTO.getFullName(), registrationDTO.getAge(), Role.STUDENT));
            Student student = studentRepository.save(new Student(account));
            studentDTO.setLogin(student.getAccount().getLogin());
            studentDTO.setPassword(student.getAccount().getPassword());
            studentDTO.setFullName(student.getAccount().getFullName());
        }
        catch (Exception ex) {
            throw ex;
        }
        return studentDTO;
    }

    @Override
    public void delete(String uuid) {
        try {
            studentRepository.deleteById(uuid);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<StudentDTO> getUserList() {
        return studentRepository.findAllStudents();
    }
}
