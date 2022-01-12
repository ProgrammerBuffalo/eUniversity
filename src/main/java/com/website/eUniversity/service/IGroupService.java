package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.entity.*;
import java.util.List;

public interface IGroupService {

    List<StudentShortInfoDTO> getAllStudents(Integer groupId);

    List<StudentDTO> getStudentsByGroup(Integer group_id);

    List<GroupDisciplineResponseDTO> getByGroupIdTeachersAndDisciplines(Integer id);

    String addGroup(String groupName);

    String editGroup(Integer group_id, String groupName);

    String deleteGroup(Integer group_id);

    StudentShortInfoDTO attachStudent(String studentId, Integer groupId);

    GroupDisciplineResponseDTO attachDiscipline(GroupDisciplineRequestDTO groupDiscipline);

    StudentShortInfoDTO detachStudent(String studentId, Integer groupId);

    GroupDisciplineResponseDTO detachDiscipline(GroupDisciplineRequestDTO groupDiscipline);

    List<DDLResponseDTO<Integer>> getGroupsDDL();
}
