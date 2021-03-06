package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.Role;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.TeacherDTO;
import com.website.eUniversity.model.dto.admin_panel.identification.RegistrationDTO;
import com.website.eUniversity.model.dto.PaginationDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Teacher;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.ITeacherRepository;
import com.website.eUniversity.service.ITeacherService;
import com.website.eUniversity.service.func.AccountSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class TeacherService extends AccountSaver implements ITeacherService {

    @Autowired
    private ITeacherRepository teacherRepository;

    @Autowired
    private IAccountRepository accountRepository;

    // After mssql will be added
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public TeacherDTO register(RegistrationDTO registrationDTO) {
        TeacherDTO teacherDTO = new TeacherDTO();
        try {
            //String encryptedPassword = passwordEncoder.encode(registrationDTO.getPassword());

            Account account = accountRepository.save(new Account(registrationDTO.getLogin(), registrationDTO.getPassword(), registrationDTO.getFullName(), registrationDTO.getAge(), Role.TEACHER));
            Teacher teacher = teacherRepository.save(new Teacher(account));
            teacherDTO.setAccountId(teacher.getAccount().getId());
            teacherDTO.setUserId(teacher.getId());
            teacherDTO.setAge(teacher.getAccount().getAge());
            teacherDTO.setLogin(teacher.getAccount().getLogin());
            teacherDTO.setPassword(teacher.getAccount().getPassword());
            teacherDTO.setFullName(teacher.getAccount().getFullName());
        }
        catch (Exception ex) {
            throw ex;
        }
        return teacherDTO;
    }

    @Override
    public TeacherDTO update(TeacherDTO user) {
        Account account = saveAccount(user);
        return new TeacherDTO(account.getId(), user.getUserId(), account.getFullName(), account.getAge(), account.getLogin(), null);
    }

    @Override
    @Transactional
    public String delete(String uuid) {
        try {
            teacherRepository.deleteByAccount_Id(uuid);
            return uuid;
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public PaginatedListDTO getUserList(PaginationDTO dto) {
        return new PaginatedListDTO<TeacherDTO>().setItems(teacherRepository
                .getPaginatedTeachers(dto.getSearch(), dto.getPageIndex(), dto.getPageSize())
                .stream()
                .map(Teacher::toDTO)
                .collect(Collectors.toList()))
                .setAllItemsCount(teacherRepository.countAllByAccount_FullNameIsLike(dto.getSearch()));
    }
}
