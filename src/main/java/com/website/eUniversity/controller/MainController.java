package com.website.eUniversity.controller;

import com.website.eUniversity.model.dto.entity.StudentDTO;
import com.website.eUniversity.model.entity.Student;
import com.website.eUniversity.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main/")
public class MainController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("s")
    public List<StudentDTO> get()
    {
        return studentService.getUserList();
    }
}
