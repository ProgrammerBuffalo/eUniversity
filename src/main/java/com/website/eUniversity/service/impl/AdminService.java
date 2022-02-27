package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.Role;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.AdminDTO;
import com.website.eUniversity.model.dto.admin_panel.identification.RegistrationDTO;
import com.website.eUniversity.model.entity.Account;
import com.website.eUniversity.model.entity.Admin;
import com.website.eUniversity.repository.IAccountRepository;
import com.website.eUniversity.repository.IAdminRepository;
import com.website.eUniversity.service.IAdminService;
import com.website.eUniversity.service.func.AccountSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

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
            adminDTO.setAccountId(admin.getAccount().getId());
            adminDTO.setUserId(admin.getId());
            adminDTO.setAge(admin.getAccount().getAge());
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
        return new AdminDTO(account.getId(), user.getUserId(), account.getFullName(), account.getAge(), account.getLogin(), null);
    }

    @Override
    @Transactional
    public String delete(String uuid) {
        try {
            adminRepository.deleteByAccount_Id(uuid);
            return uuid;
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public PaginatedListDTO getUserList(String search, Integer pageIndex, Integer pageSize) {
        return new PaginatedListDTO<AdminDTO>().setItems(adminRepository
                .getPaginatedAdmins(search, pageIndex, pageSize)
                .stream()
                .map(Admin::toDTO)
                .collect(Collectors.toList()))
                .setAllItemsCount(adminRepository.countAllByAccount_FullNameIsLike(search));
    }
}
