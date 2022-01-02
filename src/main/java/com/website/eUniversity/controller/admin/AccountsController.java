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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-panel/accounts")
@Api(value = "Accounts controller of admin panel", description = "Designed for output and registration users. \nController only works with admin's tokens.")
public class AccountsController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IAdminService adminService;

    @GetMapping("/students")
    @ApiOperation("Returns students")
    public BaseResponse<List<StudentDTO>> getStudents() {
        return new BaseResponse<List<StudentDTO>>().success(studentService.getUserList(), "Students are returned");
    }

    @GetMapping("/teachers")
    @ApiOperation("Returns teachers")
    public BaseResponse<List<TeacherDTO>> getTeachers() {
        return new BaseResponse<List<TeacherDTO>>().success(teacherService.getUserList(), "Teachers are returned");
    }

    @GetMapping("/admins")
    @ApiOperation("Returns admins")
    public BaseResponse<List<AdminDTO>> getAdmins() {
        return new BaseResponse<List<AdminDTO>>().success(adminService.getUserList(), "Teachers are returned");
    }

    @PostMapping("/register-student")
    @ApiOperation("Register new student")
    public BaseResponse<StudentDTO> registerStudent(@RequestBody RegistrationDTO registrationDTO) {
        return new BaseResponse<StudentDTO>().success(studentService.register(registrationDTO), "Student is added");
    }

    @PostMapping("/register-teacher")
    @ApiOperation("Register new teacher")
    public BaseResponse<TeacherDTO> registerTeacher(@RequestBody RegistrationDTO registrationDTO) {
        return new BaseResponse<TeacherDTO>().success(teacherService.register(registrationDTO), "Teacher is added");
    }

    @PostMapping("/register-admin")
    @ApiOperation("Register new admin")
    public BaseResponse<AdminDTO> registerAdmin(@RequestBody RegistrationDTO registrationDTO) {
        return new BaseResponse<AdminDTO>().success(adminService.register(registrationDTO), "Admin is added");
    }

}