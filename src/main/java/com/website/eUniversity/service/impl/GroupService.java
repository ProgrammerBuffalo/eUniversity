package com.website.eUniversity.service.impl;

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
import com.website.eUniversity.model.entity.*;
import com.website.eUniversity.repository.*;
import com.website.eUniversity.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService implements IGroupService {

    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private ITeacherRepository teacherRepository;

    @Autowired
    private IDisciplineRepository disciplineRepository;

    @Autowired
    private IGroupDisciplineRepository groupDisciplineRepository;

    @Override
    public PaginatedListDTO getAllGroups(PaginationDTO dto) {
        return new PaginatedListDTO<GroupDTO>().setItems(groupRepository
                .getPaginatedGroups(dto.getSearch(), dto.getPageIndex(), dto.getPageSize())
                .stream()
                .map(Group::toDTO)
                .collect(Collectors.toList()))
                .setAllItemsCount(groupRepository.countAllByGroups_Name_IsLike(dto.getSearch()));
    }

    @Override
    public List<StudentShortInfoDTO> getAllStudents(Integer groupId) {
        return groupRepository.findAllStudents(groupId);
    }

    @Override
    public List<GroupDisciplineResponseDTO> getByGroupIdTeachersAndDisciplines(Integer id) {
        List<GroupDiscipline> groupDisciplineList = groupDisciplineRepository.findByGroupIdTeachersAndDisciplines(id);

        System.out.println(groupDisciplineList.size());

        return groupDisciplineList.stream()
                .map(GroupDiscipline::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer addGroup(AddGroupDTO dto) {
        Group group = new Group();
        group.setName(dto.getName());

        groupRepository.save(group);
        return group.getId();
    }

    @Override
    public GroupDTO editGroup(UpdateGroupDTO dto) {
        Group group = findGroup(dto.getId());
        group.setName(dto.getName());

        groupRepository.save(group);
        return new GroupDTO(group.getId(), group.getName());
    }

    @Override
    public Integer deleteGroup(Integer group_id) {
        Group group = findGroup(group_id);

        groupRepository.delete(group);
        return group.getId();
    }

    @Override
    @Transactional
    public StudentShortInfoDTO attachStudent(AttachStudentDTO dto) {
        Student student = findStudent(dto.getStudentId());
        Group group = findGroup(dto.getGroupId());

        group.getStudents().add(student);
        groupRepository.save(group);

        return new StudentShortInfoDTO(student.getId(), student.getAccount().getFullName());
    }

    @Override
    @Transactional
    public GroupDisciplineResponseDTO attachDiscipline(GroupDisciplineRequestDTO groupDiscipline) {
        Teacher teacher = findTeacher(groupDiscipline.getTeacherId());
        Group group = findGroup(groupDiscipline.getGroupId());
        Discipline discipline = findDiscipline(groupDiscipline.getDisciplineId());

        groupDisciplineRepository.save(new GroupDiscipline(group, discipline, teacher));

        return new GroupDisciplineResponseDTO(discipline.getId(), teacher.getId(), discipline.getName(), teacher.getAccount().getFullName());
    }

    @Override
    @Transactional
    public StudentShortInfoDTO detachStudent(Integer studentId, Integer groupId) {
        Student student = findStudent(studentId);
        Group group = findGroup(groupId);

        group.getStudents().remove(student);
        groupRepository.save(group);

        return new StudentShortInfoDTO(student.getId(), student.getAccount().getFullName());
    }

    @Override
    @Transactional
    public GroupDisciplineResponseDTO detachDiscipline(GroupDisciplineRequestDTO groupDiscipline) throws NotFoundException {
        Teacher teacher = findTeacher(groupDiscipline.getTeacherId());
        Discipline discipline = findDiscipline(groupDiscipline.getDisciplineId());

        Group group = findGroup(groupDiscipline.getGroupId());

        Optional<GroupDiscipline> grpDiscipline = groupDisciplineRepository.findByGroup_IdAndDiscipline_IdAndTeacher_Id(group.getId(), discipline.getId(), teacher.getId());

        if (!grpDiscipline.isPresent()) {
            throw new NotFoundException("Group not found");
        }

        groupDisciplineRepository.delete(grpDiscipline.get());

        return new GroupDisciplineResponseDTO(discipline.getId(), teacher.getId(), discipline.getName(), teacher.getAccount().getFullName());
    }

    @Override
    public List<StudentDTO> getStudentsByGroup(Integer groupId) {
        return studentRepository.getAllByGroup(groupId);
    }

    private Student findStudent(Integer studentId) {

        Optional<Student> student = studentRepository.findById(studentId);

        if (!student.isPresent())
            return null;
        return student.get();
    }

    private Group findGroup(Integer groupId) {
        Optional<Group> group = groupRepository.findById(groupId);

        if (!group.isPresent())
            return null;
        return group.get();
    }

    private Teacher findTeacher(Integer teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);

        if (!teacher.isPresent())
            return null;
        return teacher.get();
    }

    private Discipline findDiscipline(Integer disciplineId) {
        Optional<Discipline> discipline = disciplineRepository.findById(disciplineId);

        if (!discipline.isPresent())
            return null;
        return discipline.get();
    }

    @Override
    public List<DDLResponseDTO<Integer>> getGroupsDDL() {
        return groupRepository.getAllGroupsDDL();
    }

    @Override
    public List<DDLResponseDTO<Integer>> getStudentsOfGroupDDL(Integer groupId) {
        return groupRepository.findStudentsOfGroup(groupId);
    }

    @Override
    public List<DDLResponseDTO<Integer>> getGroupDisciplinesDDL(Integer groupId) {
        return groupDisciplineRepository.getGroupDisciplinesDDL(groupId);
    }

    @Override
    public List<DDLResponseDTO<Integer>> findStudentsWithoutGroup() {
        return groupRepository.findStudentsWithoutGroup();
    }

    @Override
    public List<DDLResponseDTO<Integer>> getTeachersOfGroupWithDisciplineDDL(Integer groupId, Integer disciplineId) {
        return groupDisciplineRepository.findTeachersOfDisciplineAndGroup(groupId, disciplineId);
    }
}
