package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.PaginationDTO;
import com.website.eUniversity.model.dto.entity.*;
import com.website.eUniversity.model.dto.entity.account.StudentDTO;
import com.website.eUniversity.model.dto.entity.group.AddGroupDTO;
import com.website.eUniversity.model.dto.entity.group.AttachStudentDTO;
import com.website.eUniversity.model.dto.entity.group.GroupDTO;
import com.website.eUniversity.model.dto.entity.group.UpdateGroupDTO;

import java.util.List;

public interface IGroupService {

    PaginatedListDTO<GroupDTO> getAllGroups(PaginationDTO dto);

    List<StudentShortInfoDTO> getAllStudents(Integer groupId);

    List<StudentDTO> getStudentsByGroup(Integer group_id);

    List<GroupDisciplineResponseDTO> getByGroupIdTeachersAndDisciplines(Integer id);

    Integer addGroup(AddGroupDTO dto);

    GroupDTO editGroup(UpdateGroupDTO dto);

    Integer deleteGroup(Integer group_id);

    StudentShortInfoDTO attachStudent(AttachStudentDTO dto);

    GroupDisciplineResponseDTO attachDiscipline(GroupDisciplineRequestDTO groupDiscipline);

    StudentShortInfoDTO detachStudent(Integer studentId, Integer groupId);

    GroupDisciplineResponseDTO detachDiscipline(GroupDisciplineRequestDTO groupDiscipline) throws NotFoundException;

    List<DDLResponseDTO<Integer>> getGroupsDDL();

    List<DDLResponseDTO<Integer>> getStudentsOfGroupDDL(Integer groupId);

    List<DDLResponseDTO<Integer>> getGroupDisciplinesDDL(Integer groupId);

    List<DDLResponseDTO<Integer>> findStudentsWithoutGroup();

    List<DDLResponseDTO<Integer>> getTeachersOfGroupWithDisciplineDDL(Integer groupId, Integer disciplineId);
}
