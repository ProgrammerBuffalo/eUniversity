package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.Role;
import com.website.eUniversity.model.dto.entity.StudentDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.IStudentRepository;
import com.website.eUniversity.service.IStudentService;
import com.website.eUniversity.service.func.AccountSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService extends AccountSaver implements IStudentService {

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
            studentDTO.setId(student.getAccount().getId());
            studentDTO.setAge(student.getAccount().getAge());
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
    public StudentDTO update(StudentDTO user) {
        Account account = saveAccount(user);
        return new StudentDTO(account.getId(), account.getFullName(), account.getAge(), account.getLogin(), null);
    }

    @Override
    public String delete(String uuid) {
        try {
            studentRepository.deleteById(uuid);
            return uuid;
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
