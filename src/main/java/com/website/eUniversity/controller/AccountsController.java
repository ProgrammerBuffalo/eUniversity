package com.website.eUniversity.controller;

import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.StudentDTO;
import com.website.eUniversity.model.dto.TeacherDTO;
import com.website.eUniversity.service.IStudentService;
import com.website.eUniversity.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/accounts")
public class AccountsController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/students")
    public BaseResponse<List<StudentDTO>> getStudents(){
        return new BaseResponse<List<StudentDTO>>().success(studentService.getStudentList(), "Students are returned");
    }

    @GetMapping("/teachers")
    public BaseResponse<List<TeacherDTO>> getTeachers() {
        return new BaseResponse<List<TeacherDTO>>().success(teacherService.getTeacherList(), "Teachers are returned");
    }

}
