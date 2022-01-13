package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.*;
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
    public List<GroupDTO> getAllGroups() {
        List<Group> groups = groupRepository.findAll();

        return groups.stream()
                .map(group -> new GroupDTO(group.getId(), group.getName(), group.getDate()))
                .collect(Collectors.toList());
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
    public Integer addGroup(String groupName) {
        Group group = new Group();
        group.setName(groupName);

        groupRepository.save(group);
        return group.getId();
    }

    @Override
    public GroupDTO editGroup(Integer group_id, String groupName) {
        Group group = findGroup(group_id);
        group.setName(groupName);

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
    public StudentShortInfoDTO attachStudent(Integer studentId, Integer groupId) {
        Student student = findStudent(studentId);
        Group group = findGroup(groupId);

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

        if(!grpDiscipline.isPresent()){
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
    public List<DDLResponseDTO<Integer>> findStudentsWithoutGroup() {
        return groupRepository.findStudentsWithoutGroup();
    }
}
