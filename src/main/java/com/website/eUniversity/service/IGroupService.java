package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.*;
import java.util.List;

public interface IGroupService {

    List<GroupDTO> getAllGroups();

    List<StudentShortInfoDTO> getAllStudents(Integer groupId);

    List<StudentDTO> getStudentsByGroup(Integer group_id);

    List<GroupDisciplineResponseDTO> getByGroupIdTeachersAndDisciplines(Integer id);

    Integer addGroup(String groupName);

    GroupDTO editGroup(Integer group_id, String groupName);

    Integer deleteGroup(Integer group_id);

    StudentShortInfoDTO attachStudent(Integer studentId, Integer groupId);

    GroupDisciplineResponseDTO attachDiscipline(GroupDisciplineRequestDTO groupDiscipline);

    StudentShortInfoDTO detachStudent(Integer studentId, Integer groupId);

    GroupDisciplineResponseDTO detachDiscipline(GroupDisciplineRequestDTO groupDiscipline) throws NotFoundException;

    List<DDLResponseDTO<Integer>> getGroupsDDL();

    List<DDLResponseDTO<Integer>> findStudentsWithoutGroup();
}
