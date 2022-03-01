package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.Role;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.StudentDTO;
import com.website.eUniversity.model.dto.admin_panel.identification.RegistrationDTO;
import com.website.eUniversity.model.dto.student_panel.AvatarRequestDTO;
import com.website.eUniversity.model.dto.student_panel.StudentInfoDTO;
import com.website.eUniversity.model.dto.PaginationDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.File;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.IStudentRepository;
import com.website.eUniversity.service.IFileService;
import com.website.eUniversity.service.IStudentService;
import com.website.eUniversity.service.func.AccountSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class StudentService extends AccountSaver implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IFileService fileService;

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
    public ByteArrayResource getAvatar(String accountId) throws NotFoundException, IOException {
        return fileService.downloadFile(studentRepository.findByAccount_Id(accountId)
                .orElseThrow(() -> new NotFoundException("Student not found")).getImage());
    }

    @Override
    @Transactional
    public ByteArrayResource setAvatar(AvatarRequestDTO avatarRequestDTO) throws NotFoundException, IOException {
        Student student = studentRepository.findByAccount_Id(avatarRequestDTO.getAccountId())
                .orElseThrow(() -> new NotFoundException("Student not found"));

        File file = fileService.uploadFile(avatarRequestDTO.getMultipartFile());

        studentRepository.save(student.setImage(file));

        return fileService.downloadFile(file);
    }

    @Override
    public PaginatedListDTO getUserList(PaginationDTO dto) {
        return new PaginatedListDTO<StudentDTO>().setItems(studentRepository
                .getPaginatedStudents(dto.getSearch(), dto.getPageIndex(), dto.getPageSize())
                .stream()
                .map(Student::toDTO)
                .collect(Collectors.toList()))
                .setAllItemsCount(studentRepository.countAllByAccount_FullNameIsLike(dto.getSearch()));
    }

}