package com.website.eUniversity.controller.admin;

import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.entity.AdminDTO;
import com.website.eUniversity.model.dto.entity.StudentDTO;
import com.website.eUniversity.model.dto.entity.TeacherDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.service.IAdminService;
import com.website.eUniversity.service.IStudentService;
import com.website.eUniversity.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse<?>> getStudents(@RequestParam("search") String search,
                                                       @RequestParam("pageIndex") Integer pageIndex,
                                                       @RequestParam("pageSize") Integer pageSize) {
        return ResponseEntity.ok(new BaseResponse<PaginatedListDTO>().success(studentService.getUserList(search, pageIndex, pageSize), "Students are returned"));
    }

    @GetMapping("/teachers")
    @ApiOperation("Returns teachers")
    public ResponseEntity<BaseResponse<?>> getTeachers(@RequestParam("search") String search,
                                                       @RequestParam("pageIndex") Integer pageIndex,
                                                       @RequestParam("pageSize") Integer pageSize) {
        return ResponseEntity.ok(new BaseResponse<PaginatedListDTO>().success(teacherService.getUserList(search, pageIndex, pageSize), "Teachers are returned"));
    }

    @GetMapping("/admins")
    @ApiOperation("Returns admins")
    public ResponseEntity<BaseResponse<?>> getAdmins(@RequestParam("search") String search,
                                                     @RequestParam("pageIndex") Integer pageIndex,
                                                     @RequestParam("pageSize") Integer pageSize) {
        return ResponseEntity.ok(new BaseResponse<PaginatedListDTO>().success(adminService.getUserList(search, pageIndex, pageSize), "Teachers are returned"));
    }

    @PostMapping("/register-student")
    @ApiOperation("Register new student")
    public ResponseEntity<BaseResponse<StudentDTO>> registerStudent(@RequestBody RegistrationDTO registrationDTO) {
        return ResponseEntity.ok(new BaseResponse<StudentDTO>().success(studentService.register(registrationDTO), "Student is added"));
    }

    @PostMapping("/register-teacher")
    @ApiOperation("Register new teacher")
    public ResponseEntity<BaseResponse<TeacherDTO>> registerTeacher(@RequestBody RegistrationDTO registrationDTO) {
        return ResponseEntity.ok(new BaseResponse<TeacherDTO>().success(teacherService.register(registrationDTO), "Teacher is added"));
    }

    @PostMapping("/register-admin")
    @ApiOperation("Register new admin")
    public ResponseEntity<BaseResponse<AdminDTO>> registerAdmin(@RequestBody RegistrationDTO registrationDTO) {
        return ResponseEntity.ok(new BaseResponse<AdminDTO>().success(adminService.register(registrationDTO), "Admin is added"));
    }

    @PutMapping("/update-student")
    @ApiOperation("Update student")
    public ResponseEntity<BaseResponse<StudentDTO>> updateStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(new BaseResponse<StudentDTO>().success(studentService.update(studentDTO), "Student is updated"));
    }

    @PutMapping("/update-teacher")
    @ApiOperation("Update teacher")
    public ResponseEntity<BaseResponse<TeacherDTO>> updateTeacher(@RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok(new BaseResponse<TeacherDTO>().success(teacherService.update(teacherDTO), "Teacher is updated"));
    }

    @PutMapping("/update-admin")
    @ApiOperation("Update admin")
    public ResponseEntity<BaseResponse<AdminDTO>> updateAdmin(@RequestBody AdminDTO adminDTO) {
        return ResponseEntity.ok(new BaseResponse<AdminDTO>().success(adminService.update(adminDTO), "Admin is updated"));
    }

    @DeleteMapping("/delete-student")
    @ApiOperation("Delete student")
    public ResponseEntity<BaseResponse<String>> deleteStudent(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok(new BaseResponse<String>().success(studentService.delete(id), "Student is deleted"));
    }

    @DeleteMapping("/delete-teacher")
    @ApiOperation("Delete teacher")
    public ResponseEntity<BaseResponse<String>> deleteTeacher(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok(new BaseResponse<String>().success(teacherService.delete(id), "Teacher is deleted"));
    }

    @DeleteMapping("/delete-admin")
    @ApiOperation("Delete admin")
    public ResponseEntity<BaseResponse<String>> deleteAdmin(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok(new BaseResponse<String>().success(adminService.delete(id), "Admin is deleted"));
    }
}