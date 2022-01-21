package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.AttachScheduleDTO;
import com.website.eUniversity.model.dto.entity.ScheduleDisciplineDTO;

import java.util.List;

public interface IScheduleService {

    List<ScheduleDisciplineDTO> findScheduleForGroup(Integer groupId);

    ScheduleDisciplineDTO attachSchedule(AttachScheduleDTO attachScheduleDTO) throws NotFoundException;

    ScheduleDisciplineDTO detachSchedule(Integer groupId, Integer disciplineId, Integer teacherId) throws NotFoundException;
}
