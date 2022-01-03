package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.Role;
import com.website.eUniversity.model.dto.entity.AdminDTO;
import com.website.eUniversity.model.dto.entity.TeacherDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Admin;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.IAdminRepository;
import com.website.eUniversity.service.IAdminService;
import com.website.eUniversity.service.func.AccountSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdminService extends AccountSaver implements IAdminService {

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private IAccountRepository accountRepository;

    // After mssql will be added
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AdminDTO register(RegistrationDTO registrationDTO) {
        AdminDTO adminDTO = new AdminDTO();
        try {
            //String encryptedPassword = passwordEncoder.encode(registrationDTO.getPassword());

            Account account = accountRepository.save(new Account(registrationDTO.getLogin(), registrationDTO.getPassword(), registrationDTO.getFullName(), registrationDTO.getAge(), Role.ADMIN));
            Admin admin = adminRepository.save(new Admin(account));
            adminDTO.setLogin(admin.getAccount().getLogin());
            adminDTO.setPassword(admin.getAccount().getPassword());
            adminDTO.setFullName(admin.getAccount().getFullName());
        }
        catch (Exception ex) {
            throw ex;
        }
        return adminDTO;
    }

    @Override
    public AdminDTO update(AdminDTO user) {
        Account account = saveAccount(user);
        return new AdminDTO(account.getId(), account.getFullName(), account.getAge(), account.getLogin(), null);
    }

    @Override
    public String delete(String uuid) {
        try {
            adminRepository.deleteById(uuid);
            return uuid;
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<AdminDTO> getUserList() {
        return adminRepository.findAllAdmins();
    }
}
