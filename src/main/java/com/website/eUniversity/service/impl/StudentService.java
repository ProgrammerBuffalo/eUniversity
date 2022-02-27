package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.Role;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.StudentDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.StudentShortInfoDTO;
import com.website.eUniversity.model.dto.admin_panel.identification.RegistrationDTO;
import com.website.eUniversity.model.dto.student_panel.StudentInfoDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.IStudentRepository;
import com.website.eUniversity.service.IStudentService;
import com.website.eUniversity.service.func.AccountSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

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
            studentDTO.setAccountId(student.getAccount().getId());
            studentDTO.setUserId(student.getId());
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
        return new StudentDTO(account.getId(), user.getUserId(), account.getFullName(), account.getAge(), account.getLogin(), null);
    }

    @Override
    @Transactional
    public String delete(String uuid) {
        try {
            studentRepository.deleteByAccount_Id(uuid);
            return uuid;
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public StudentInfoDTO getStudentInfo(String accountId) throws NotFoundException {
        return studentRepository.findByAccount(accountRepository.findAccountById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found")))
                    .map(StudentInfoDTO::toDTO)
                    .orElseThrow(() -> new NotFoundException("Student not found"));
    }

    @Override
    public PaginatedListDTO getUserList(String search, Integer pageIndex, Integer pageSize) {
        return new PaginatedListDTO<StudentDTO>().setItems(studentRepository
                .getPaginatedStudents(search, pageIndex, pageSize)
                .stream()
                .map(Student::toDTO)
                .collect(Collectors.toList()))
                .setAllItemsCount(studentRepository.countAllByAccount_FullNameIsLike(search));
    }

}