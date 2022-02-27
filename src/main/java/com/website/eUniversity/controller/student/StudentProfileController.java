package com.website.eUniversity.controller.student;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.student_panel.StudentInfoDTO;
import com.website.eUniversity.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student-panel/profile")
public class StudentProfileController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/short-info")
    public ResponseEntity<BaseResponse<?>> getShortInfo(@RequestParam("accountId") String accountId) throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<StudentInfoDTO>().success(studentService.getStudentInfo(accountId), "Profile is returned"));
    }
}
