package com.website.eUniversity.controller.admin;

import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.entity.JournalItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin-panel/journal")
public class JournalController {

    public ResponseEntity<BaseResponse<List<JournalItemDTO>>> getStudentJournal(@RequestParam("studentId") Integer studentId) {
        return null;
    }
}
