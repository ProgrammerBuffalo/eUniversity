package com.website.eUniversity.service.impl;

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
    private IAccountRepository accountRepository;

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
    public String addGroup(String groupName) {
        Group group = new Group();
        group.setName(groupName);

        groupRepository.save(group);
        return group.getName();
    }

    @Override
    public String editGroup(Integer group_id, String groupName) {
        Group group = findGroup(group_id);
        group.setName(groupName);

        groupRepository.save(group);
        return group.getName();
    }

    @Override
    public String deleteGroup(Integer group_id) {
        Group group = findGroup(group_id);

        groupRepository.delete(group);
        return group.getName();
    }

    @Override
    @Transactional
    public StudentShortInfoDTO attachStudent(String studentId, Integer groupId) {
        Student student = findStudent(studentId);
        Group group = findGroup(groupId);

        group.getStudents().add(student);
        groupRepository.save(group);

        return new StudentShortInfoDTO(student.getAccount().getFullName());
    }

    @Override
    @Transactional
    public GroupDisciplineResponseDTO attachDiscipline(GroupDisciplineRequestDTO groupDiscipline) {
        Teacher teacher = findTeacher(groupDiscipline.getTeacherId());
        Group group = findGroup(groupDiscipline.getGroupId());
        Discipline discipline = findDiscipline(groupDiscipline.getDisciplineId());

        groupDisciplineRepository.save(new GroupDiscipline(group, discipline, teacher));

        return new GroupDisciplineResponseDTO(group.getName(), discipline.getName(), teacher.getAccount().getFullName());
    }

    @Override
    @Transactional
    public StudentShortInfoDTO detachStudent(String studentId, Integer groupId) {
        Student student = findStudent(studentId);
        Group group = findGroup(groupId);

        group.getStudents().remove(student);
        groupRepository.save(group);

        return new StudentShortInfoDTO(student.getAccount().getFullName());
    }

    @Override
    @Transactional
    public GroupDisciplineResponseDTO detachDiscipline(GroupDisciplineRequestDTO groupDiscipline) {
        Teacher teacher = findTeacher(groupDiscipline.getTeacherId());
        Group group = findGroup(groupDiscipline.getGroupId());
        Discipline discipline = findDiscipline(groupDiscipline.getDisciplineId());

        groupDisciplineRepository.delete(new GroupDiscipline(group, discipline, teacher));

        return new GroupDisciplineResponseDTO(group.getName(), discipline.getName(), teacher.getAccount().getFullName());
    }

    @Override
    public List<StudentDTO> getStudentsByGroup(Integer groupId) {
        return studentRepository.getAllByGroup(groupId);
    }

    private Student findStudent(String studentId) {
        Optional<Account> account = accountRepository.findAccountById(studentId);

        if (!account.isPresent())
            return null;

        Optional<Student> student = studentRepository.findByAccount(account.get());

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

    private Teacher findTeacher(String teacherId) {
        Optional<Account> account = accountRepository.findAccountById(teacherId);

        if (!account.isPresent())
            return null;

        Optional<Teacher> teacher = teacherRepository.findByAccount(account.get());

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
}
