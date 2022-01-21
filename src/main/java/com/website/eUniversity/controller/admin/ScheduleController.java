package com.website.eUniversity.controller.admin;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.entity.AttachScheduleDTO;
import com.website.eUniversity.model.dto.entity.ScheduleDisciplineDTO;
import com.website.eUniversity.service.IScheduleService;
import com.website.eUniversity.service.impl.ScheduleService;
import io.swagger.annotations.Api;
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

    @GetMapping("/get-group-schedule")
    public ResponseEntity<BaseResponse<List<ScheduleDisciplineDTO>>> getGroupSchedule(@RequestParam("id") Integer groupId) {
        return ResponseEntity.ok(new BaseResponse<List<ScheduleDisciplineDTO>>().success(scheduleService.findScheduleForGroup(groupId), "OK"));
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