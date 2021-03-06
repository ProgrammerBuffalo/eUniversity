package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.admin_panel.entity.AttachScheduleDTO;
import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.ScheduleDisciplineDTO;
import com.website.eUniversity.model.entity.EducationalProcess;
import com.website.eUniversity.model.entity.GroupDiscipline;
import com.website.eUniversity.model.entity.Schedule;
import com.website.eUniversity.repository.IEducationalProcessRepository;
import com.website.eUniversity.repository.IGroupDisciplineRepository;
import com.website.eUniversity.repository.IScheduleRepository;
import com.website.eUniversity.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private IGroupDisciplineRepository groupDisciplineRepository;

    @Autowired
    private IEducationalProcessRepository educationalProcessRepository;

    @Override
    public List<ScheduleDisciplineDTO> findScheduleLessonsForGroup(Integer groupId) {
        return findScheduleDiscipline(groupId, scheduleRepository.findAllLessonsByGroupId(groupId));
    }

    @Override
    public List<ScheduleDisciplineDTO> findScheduleExamsForGroup(Integer groupId) {
        return findScheduleDiscipline(groupId, scheduleRepository.findAllExamsByGroupId(groupId));
    }

    @Override
    public ScheduleDisciplineDTO attachSchedule(AttachScheduleDTO attachScheduleDTO) throws NotFoundException {
        Optional<GroupDiscipline> groupDiscipline = groupDisciplineRepository.findByGroup_IdAndDiscipline_IdAndTeacher_Id(attachScheduleDTO.getGroupId(), attachScheduleDTO.getDisciplineId(), attachScheduleDTO.getTeacherId());

        if(!groupDiscipline.isPresent())
            throw new NotFoundException("Group, Discipline or Teacher not found");

        Optional<EducationalProcess> educationalProcess = educationalProcessRepository.findById(attachScheduleDTO.getEducationalProcessId());

        if(!educationalProcess.isPresent())
            throw new NotFoundException("Educational process not found");

        Schedule schedule = new Schedule(attachScheduleDTO.getFrom(), attachScheduleDTO.getTo(), attachScheduleDTO.getWeekNum(), groupDiscipline.get(), educationalProcess.get());

        schedule = scheduleRepository.save(schedule);

        return Schedule.toDisciplineDto(schedule).setItemList(new ArrayList<>(Arrays.asList(Schedule.toItemDto(schedule))));
    }

    @Override
    public ScheduleDisciplineDTO detachSchedule(Integer scheduleId) throws NotFoundException {

        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);

        if(!schedule.isPresent())
            throw new NotFoundException("Schedule not found");

        scheduleRepository.delete(schedule.get());

        return Schedule.toDisciplineDto(schedule.get()).setItemList(new ArrayList<>(Arrays.asList(Schedule.toItemDto(schedule.get()))));

    }

    @Override
    public List<DDLResponseDTO<Integer>> findLessonsDDL() {
        return educationalProcessRepository.findLessons();
    }

    @Override
    public List<DDLResponseDTO<Integer>> findExamsDDL() {
        return educationalProcessRepository.findExams();
    }

    @Override
    public  List<DDLResponseDTO<Integer>> getAllEducationProcessesDDL(){
        return  educationalProcessRepository.getAll();
    }

    private List<ScheduleDisciplineDTO> findScheduleDiscipline(Integer groupId, List<Schedule> schedules) {
        List<GroupDiscipline> groupDisciplineList = groupDisciplineRepository.findByGroupIdTeachersAndDisciplines(groupId);

        List<ScheduleDisciplineDTO> scheduleDisciplineDTOList =  groupDisciplineList.stream().collect(Collectors.toMap(GroupDiscipline::getDisciplineId, id -> id, (id, val) -> id))
                .values().stream().map(groupDiscipline -> new ScheduleDisciplineDTO()
                        .setDisciplineId(groupDiscipline.getDiscipline().getId())
                        .setDisciplineName(groupDiscipline.getDiscipline().getName()))
                .collect(Collectors.toList());

        for(Schedule schedule : schedules) {
            GroupDiscipline groupDiscipline = schedule.getGroupDiscipline();

            Optional<ScheduleDisciplineDTO> itemScheduleDiscipline = scheduleDisciplineDTOList.stream().filter(scheduleDisciplineDTO ->
                    scheduleDisciplineDTO.getDisciplineId() == groupDiscipline.getDiscipline().getId()).findAny();

            if(itemScheduleDiscipline.isPresent())
                itemScheduleDiscipline.get().getItemList().add(Schedule.toItemDto(schedule));
        }

        return scheduleDisciplineDTOList;
    }
}
