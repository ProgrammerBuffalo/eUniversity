package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.GroupDiscipline;
import com.website.eUniversity.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findAllByGroupDiscipline_Group_Id(Integer groupId);

    Optional<Schedule> findByGroupDiscipline(GroupDiscipline groupDiscipline);
}
