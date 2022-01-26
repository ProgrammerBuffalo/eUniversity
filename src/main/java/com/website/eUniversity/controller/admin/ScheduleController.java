package com.website.eUniversity.controller.admin;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.entity.AttachScheduleDTO;
import com.website.eUniversity.model.dto.entity.DDLResponseDTO;
import com.website.eUniversity.model.dto.entity.ScheduleDisciplineDTO;
import com.website.eUniversity.service.IScheduleService;
import com.website.eUniversity.service.impl.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-panel/schedule")
@Api(value = "Schedule controller of admin panel", description = "Designed to work with schedule. \nController only works with admin's tokens.")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @GetMapping("/get-group-schedule-lessons")
    public ResponseEntity<BaseResponse<List<ScheduleDisciplineDTO>>> getGroupScheduleLessons(@RequestParam("id") Integer groupId) {
        return ResponseEntity.ok(new BaseResponse<List<ScheduleDisciplineDTO>>().success(scheduleService.findScheduleLessonsForGroup(groupId), "OK"));
    }

    @GetMapping("/get-group-schedule-exams")
    public ResponseEntity<BaseResponse<List<ScheduleDisciplineDTO>>> getGroupScheduleExams(@RequestParam("id") Integer groupId) {
        return ResponseEntity.ok(new BaseResponse<List<ScheduleDisciplineDTO>>().success(scheduleService.findScheduleExamsForGroup(groupId), "OK"));
    }

    @GetMapping("/get-lessons-ddl")
    @ApiOperation("Returns Lesson/Seminar/Practise by ddl")
    public ResponseEntity<BaseResponse<List<DDLResponseDTO<Integer>>>> getLessonsDDL() {
        return ResponseEntity.ok(new BaseResponse<List<DDLResponseDTO<Integer>>>().success(scheduleService.findLessonsDDL(), "OK"));
    }

    @GetMapping("/get-exams-ddl")
    @ApiOperation("Returns Exam/Midterm by ddl")
    public ResponseEntity<BaseResponse<List<DDLResponseDTO<Integer>>>> getExamsDDL() {
        return ResponseEntity.ok(new BaseResponse<List<DDLResponseDTO<Integer>>>().success(scheduleService.findExamsDDL(), "OK"));
    }

    @GetMapping("/get-education-processes-ddl")
    @ApiOperation("get`s all education processes for drop down")
    public ResponseEntity<BaseResponse<List<DDLResponseDTO<Integer>>>> getAllEducationProcesses() {
        return ResponseEntity.ok(new BaseResponse<List<DDLResponseDTO<Integer>>>().success(scheduleService.getAllEducationProcessesDDL(), "OK"));
    }

    @PostMapping("/attach-schedule-row")
    public ResponseEntity<BaseResponse<ScheduleDisciplineDTO>> attachRow(@RequestBody AttachScheduleDTO attachScheduleDTO)
            throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<ScheduleDisciplineDTO>().success(scheduleService.attachSchedule(attachScheduleDTO), "OK"));
    }

    @DeleteMapping("/detach-schedule-row")
    public ResponseEntity<BaseResponse<ScheduleDisciplineDTO>> detachRow(@RequestParam("scheduleId") Integer scheduleId) throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<ScheduleDisciplineDTO>().success(scheduleService.detachSchedule(scheduleId), "OK"));
    }
}