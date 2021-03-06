package com.website.eUniversity.runner;

import com.website.eUniversity.model.dto.admin_panel.identification.RegistrationDTO;
import com.website.eUniversity.service.IAdminService;
import com.website.eUniversity.service.IStudentService;
import com.website.eUniversity.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("h2")
public class DataLoader implements ApplicationRunner {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        studentService.register(new RegistrationDTO("student1", "student1", "Eldar Eyvazov", 20));
        studentService.register(new RegistrationDTO("student2", "student2", "Emil Abbas", 10));
        teacherService.register(new RegistrationDTO("teacher1", "teacher1", "Dmitriy", 30));
        adminService.register(new RegistrationDTO("admin1", "admin1", "Administer", 40));
    }
}
