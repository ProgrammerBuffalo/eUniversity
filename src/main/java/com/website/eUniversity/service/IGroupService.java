package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.entity.GroupDisciplineRequestDTO;
import com.website.eUniversity.model.dto.entity.GroupDisciplineTeacherDTO;
import com.website.eUniversity.model.dto.entity.StudentShortInfoDTO;

import java.util.List;

public interface IGroupService {

    List<StudentShortInfoDTO> getAllStudents(Integer groupId);

    List<GroupDisciplineTeacherDTO> getAllGroupDisciplineTeacher();

    String addGroup(String groupName);

    String editGroup(Integer group_id, String groupName);

    String deleteGroup(Integer group_id);

    StudentShortInfoDTO attachStudent(String studentId, Integer groupId);

    GroupDisciplineTeacherDTO attachDiscipline(GroupDisciplineRequestDTO groupDiscipline);

    StudentShortInfoDTO detachStudent(String studentId, Integer groupId);

    GroupDisciplineTeacherDTO detachDiscipline(GroupDisciplineRequestDTO groupDiscipline);
}
