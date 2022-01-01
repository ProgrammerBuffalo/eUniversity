package com.website.eUniversity.controller.admin;

import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.entity.AdminDTO;
import com.website.eUniversity.model.dto.entity.StudentDTO;
import com.website.eUniversity.model.dto.entity.TeacherDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.model.entity.Teacher;
import com.website.eUniversity.service.IAdminService;
import com.website.eUniversity.service.IStudentService;
import com.website.eUniversity.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-panel/accounts")
public class AccountsController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IAdminService adminService;

    @GetMapping("/students")
    public BaseResponse<List<StudentDTO>> getStudents() {
        return new BaseResponse<List<StudentDTO>>().success(studentService.getUserList(), "Students are returned");
    }

    @GetMapping("/teachers")
    public BaseResponse<List<TeacherDTO>> getTeachers() {
        return new BaseResponse<List<TeacherDTO>>().success(teacherService.getUserList(), "Teachers are returned");
    }

    @GetMapping("/admins")
    public BaseResponse<List<AdminDTO>> getAdmins() {
        return new BaseResponse<List<AdminDTO>>().success(adminService.getUserList(), "Teachers are returned");
    }

    @PostMapping("/register-student")
    public BaseResponse<StudentDTO> registerStudent(@RequestBody RegistrationDTO registrationDTO) {
        return new BaseResponse<StudentDTO>().success(studentService.register(registrationDTO), "Student is added");
    }

    @PostMapping("/register-teacher")
    public BaseResponse<TeacherDTO> registerTeacher(@RequestBody RegistrationDTO registrationDTO) {
        return new BaseResponse<TeacherDTO>().success(teacherService.register(registrationDTO), "Teacher is added");
    }

    @PostMapping("/register-admin")
    public BaseResponse<AdminDTO> registerAdmin(@RequestBody RegistrationDTO registrationDTO) {
        return new BaseResponse<AdminDTO>().success(adminService.register(registrationDTO), "Admin is added");
    }

}