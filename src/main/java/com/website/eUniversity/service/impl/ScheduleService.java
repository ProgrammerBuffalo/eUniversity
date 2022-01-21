package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.AttachScheduleDTO;
import com.website.eUniversity.model.dto.entity.ScheduleDisciplineDTO;
import com.website.eUniversity.model.dto.entity.ScheduleItemDTO;
import com.website.eUniversity.model.entity.GroupDiscipline;
import com.website.eUniversity.model.entity.Schedule;
import com.website.eUniversity.repository.IGroupDisciplineRepository;
import com.website.eUniversity.repository.IScheduleRepository;
import com.website.eUniversity.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private IGroupDisciplineRepository groupDisciplineRepository;

    @Override
    public List<ScheduleDisciplineDTO> findScheduleForGroup(Integer groupId) {
        List<Schedule> schedules = scheduleRepository.findByGroupDiscipline_Group_Id(groupId);

        List<ScheduleDisciplineDTO> scheduleDisciplineDTOList = new ArrayList<>();

        for(Schedule schedule : schedules) {
            GroupDiscipline groupDiscipline = schedule.getGroupDiscipline();

            Optional<ScheduleDisciplineDTO> itemScheduleDiscipline = scheduleDisciplineDTOList.stream().filter(scheduleDisciplineDTO ->
                    scheduleDisciplineDTO.getDisciplineId() == groupDiscipline.getDiscipline().getId()).findAny();

            if(itemScheduleDiscipline.isPresent())
                itemScheduleDiscipline.get().getItemList().add(Schedule.toItemDto(schedule));
            else
                scheduleDisciplineDTOList.add(Schedule.toDisciplineDto(schedule));
        }

        return scheduleDisciplineDTOList;
    }

    @Override
    public ScheduleDisciplineDTO attachSchedule(AttachScheduleDTO attachScheduleDTO) throws NotFoundException {
        Optional<GroupDiscipline> groupDiscipline = groupDisciplineRepository.findByGroup_IdAndDiscipline_IdAndTeacher_Id(attachScheduleDTO.getGroupId(), attachScheduleDTO.getDisciplineId(), attachScheduleDTO.getTeacherId());

        if(!groupDiscipline.isPresent())
            throw new NotFoundException("Group, Discipline or Teacher not found");

        Schedule schedule = new Schedule(attachScheduleDTO.getFrom(), attachScheduleDTO.getTo(), attachScheduleDTO.getWeekNum(), groupDiscipline.get());

        schedule = scheduleRepository.save(schedule);

        return Schedule.toDisciplineDto(schedule).setItemList(new ArrayList<>(Arrays.asList(Schedule.toItemDto(schedule))));
    }

    @Override
    public ScheduleDisciplineDTO detachSchedule(Integer groupId, Integer disciplineId, Integer teacherId) throws NotFoundException {
        Optional<GroupDiscipline> groupDiscipline = groupDisciplineRepository.findByGroup_IdAndDiscipline_IdAndTeacher_Id(groupId, disciplineId, teacherId);

        if(!groupDiscipline.isPresent())
            throw new NotFoundException("Group, Discipline or Teacher not found");

        Optional<Schedule> schedule = scheduleRepository.findByGroupDiscipline(groupDiscipline.get());

        if(!schedule.isPresent())
            throw new NotFoundException("Schedule not found");

        scheduleRepository.delete(schedule.get());

        return Schedule.toDisciplineDto(schedule.get()).setItemList(new ArrayList<>(Arrays.asList(Schedule.toItemDto(schedule.get()))));

    }
}
