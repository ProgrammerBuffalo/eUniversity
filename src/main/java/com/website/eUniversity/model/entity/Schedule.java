package com.website.eUniversity.model.entity;

import com.website.eUniversity.model.dto.entity.ScheduleDisciplineDTO;
import com.website.eUniversity.model.dto.entity.ScheduleItemDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Schedule", uniqueConstraints={
        @UniqueConstraint(columnNames = {"groupDiscipline_id", "id"})
})
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "timeFrom")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh:mm")
    private Date timeFrom;

    @Column(name = "timeTo")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh:mm")
    private Date timeTo;

    @Column(name = "day")
    private Integer day;

    @ManyToOne
    @JoinColumn(name = "groupDiscipline_id")
    private GroupDiscipline groupDiscipline;

    @ManyToOne
    @JoinColumn(name = "educationalProcess_id")
    private EducationalProcess educationalProcess;

    public static ScheduleItemDTO toItemDto(Schedule schedule) {
        return new ScheduleItemDTO()
                .setScheduleId(schedule.getId())
                .setTeacherId(schedule.groupDiscipline.getTeacher().getId())
                .setTeacherName(schedule.groupDiscipline.getTeacher().getAccount().getFullName())
                .setWeekNum(schedule.day)
                .setFrom(schedule.timeFrom)
                .setTo(schedule.timeTo)
                .setType(schedule.educationalProcess.getName());
    }

    public static ScheduleDisciplineDTO toDisciplineDto(Schedule schedule) {
        return new ScheduleDisciplineDTO()
                .setDisciplineId(schedule.groupDiscipline.getDiscipline().getId())
                .setDisciplineName(schedule.groupDiscipline.getDiscipline().getName());
    }

    public Schedule() {

    }

    public Schedule(Date timeFrom, Date timeTo, Integer day, GroupDiscipline groupDiscipline, EducationalProcess educationalProcess) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.day = day;
        this.groupDiscipline = groupDiscipline;
        this.educationalProcess = educationalProcess;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public GroupDiscipline getGroupDiscipline() {
        return groupDiscipline;
    }

    public void setGroupDiscipline(GroupDiscipline groupDiscipline) {
        this.groupDiscipline = groupDiscipline;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date to) {
        this.timeTo = to;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date from) {
        this.timeFrom = from;
    }

    public EducationalProcess getEducationalProcess() {
        return educationalProcess;
    }

    public void setEducationalProcess(EducationalProcess educationalProcess) {
        this.educationalProcess = educationalProcess;
    }
}