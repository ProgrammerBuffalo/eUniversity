package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.admin_panel.entity.AttachScheduleDTO;
import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.ScheduleDisciplineDTO;

import java.util.List;

public interface IScheduleService {

    List<ScheduleDisciplineDTO> findScheduleLessonsForGroup(Integer groupId);

    List<ScheduleDisciplineDTO> findScheduleExamsForGroup(Integer groupId);

    ScheduleDisciplineDTO attachSchedule(AttachScheduleDTO attachScheduleDTO) throws NotFoundException;

    ScheduleDisciplineDTO detachSchedule(Integer scheduleId) throws NotFoundException;

    List<DDLResponseDTO<Integer>> findLessonsDDL();

    List<DDLResponseDTO<Integer>> findExamsDDL();

    List<DDLResponseDTO<Integer>> getAllEducationProcessesDDL();
}
