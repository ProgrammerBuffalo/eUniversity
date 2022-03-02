package com.website.eUniversity.controller.admin;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.admin_panel.entity.JournalItemDTO;
import com.website.eUniversity.service.IJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-panel/journal")
public class AdminJournalController {

    @Autowired
    private IJournalService journalService;

    @GetMapping("get-student-journal")
    public ResponseEntity<BaseResponse<List<JournalItemDTO>>> getStudentJournal(@RequestParam("studentId") Integer studentId) {
        return ResponseEntity.ok(new BaseResponse<List<JournalItemDTO>>().success(journalService.getJournalItemsByStudent(studentId), "OK"));
    }

    @PutMapping("edit-journal-item")
    public ResponseEntity<BaseResponse<JournalItemDTO>> editJournalItem(@RequestBody JournalItemDTO itemDTO) {
        return ResponseEntity.ok(new BaseResponse<JournalItemDTO>().success(journalService.editItemOfJournal(itemDTO), "OK"));
    }

    @DeleteMapping("remove-journal-item")
    public ResponseEntity<BaseResponse<JournalItemDTO>> removeJournalItem(@RequestParam("journalId") Integer journalId)
            throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<JournalItemDTO>().success(journalService.removeItemOfJournal(journalId), "OK"));
    }

}
