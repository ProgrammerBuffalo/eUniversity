package com.website.eUniversity.controller.student;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.admin_panel.entity.MaterialRequestDTO;
import com.website.eUniversity.model.dto.student_panel.AvatarRequestDTO;
import com.website.eUniversity.model.dto.student_panel.StudentInfoDTO;
import com.website.eUniversity.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("student-panel/profile")
public class StudentProfileController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/short-info")
    public ResponseEntity<BaseResponse<?>> getShortInfo(@RequestParam("accountId") String accountId) throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<StudentInfoDTO>().success(studentService.getStudentInfo(accountId), "Profile is returned"));
    }

    @GetMapping("/get-avatar")
    public ResponseEntity<BaseResponse<?>> getAvatar(@RequestParam("accountId") String accountId)
            throws NotFoundException, IOException {
        ByteArrayResource byteArrayResource = studentService.getAvatar(accountId);
        return ResponseEntity
                .ok()
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new BaseResponse<>().success(byteArrayResource, "Avatar successfully downloaded"));
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<BaseResponse<?>> upload(@ModelAttribute AvatarRequestDTO avatarRequestDTO)
            throws NotFoundException, IOException {
        ByteArrayResource byteArrayResource = studentService.setAvatar(avatarRequestDTO);

        return ResponseEntity
                .ok()
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new BaseResponse<>().success(byteArrayResource, "Avatar successfully uploaded"));
    }

}
