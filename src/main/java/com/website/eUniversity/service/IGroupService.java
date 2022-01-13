package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.*;
import com.website.eUniversity.model.dto.entity.group.AddGroupDTO;
import com.website.eUniversity.model.dto.entity.group.UpdateGroupDTO;

import java.util.List;

public interface IGroupService {

    List<GroupDTO> getAllGroups();

    List<StudentShortInfoDTO> getAllStudents(Integer groupId);

    List<StudentDTO> getStudentsByGroup(Integer group_id);

    List<GroupDisciplineResponseDTO> getByGroupIdTeachersAndDisciplines(Integer id);

    Integer addGroup(AddGroupDTO dto);

    GroupDTO editGroup(UpdateGroupDTO dto);

    Integer deleteGroup(Integer group_id);

    StudentShortInfoDTO attachStudent(Integer studentId, Integer groupId);

    GroupDisciplineResponseDTO attachDiscipline(GroupDisciplineRequestDTO groupDiscipline);

    StudentShortInfoDTO detachStudent(Integer studentId, Integer groupId);

    GroupDisciplineResponseDTO detachDiscipline(GroupDisciplineRequestDTO groupDiscipline) throws NotFoundException;

    List<DDLResponseDTO<Integer>> getGroupsDDL();

    List<DDLResponseDTO<Integer>> findStudentsWithoutGroup();
}
