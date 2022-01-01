package com.website.eUniversity.controller.admin;

import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.entity.GroupDisciplineRequestDTO;
import com.website.eUniversity.model.dto.entity.GroupDisciplineTeacherDTO;
import com.website.eUniversity.model.dto.entity.StudentShortInfoDTO;
import com.website.eUniversity.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-panel/groups")
public class GroupsController {

    @Autowired
    private IGroupService groupService;

    @GetMapping("/get-students-group")
    public BaseResponse<List<StudentShortInfoDTO>> getStudentsGroup(@RequestParam(name = "group_id") Integer group_id) {
        return new BaseResponse<List<StudentShortInfoDTO>>().success(groupService.getAllStudents(group_id), "Students are returned");
    }

    @GetMapping("/get-all-groups")
    public BaseResponse<List<GroupDisciplineTeacherDTO>> getAllGroups() {
        return new BaseResponse<List<GroupDisciplineTeacherDTO>>().success(groupService.getAllGroupDisciplineTeacher(), "Ok");
    }

    @PostMapping("/attach-student")
    public BaseResponse<StudentShortInfoDTO> attachStudent(@RequestParam(name = "group_id") Integer group_id,
                                                           @RequestParam(name = "student_id") String student_id) {
        return new BaseResponse<StudentShortInfoDTO>().success(groupService.attachStudent(student_id, group_id), "OK");
    }

    @PostMapping("/add-group")
    public BaseResponse<String> addGroup(@RequestParam(name = "group_name") String group_name) {
        return new BaseResponse<String>().success(groupService.addGroup(group_name), "OK");
    }

    @PostMapping("/attach-discipline")
    public BaseResponse<GroupDisciplineTeacherDTO> attachDiscipline(@RequestBody GroupDisciplineRequestDTO groupDisciplineRequestDTO) {
        return new BaseResponse<GroupDisciplineTeacherDTO>().success(groupService.attachDiscipline(groupDisciplineRequestDTO), "OK");
    }

    @PutMapping("/edit-group-info")
    public BaseResponse<String> editGroup(@RequestParam(name = "group_id") Integer group_id,
                                          @RequestParam(name = "group_name") String group_name) {
        return new BaseResponse<String>().success(groupService.editGroup(group_id, group_name), "OK");
    }

    @DeleteMapping("/detach-discipline")
    public BaseResponse<GroupDisciplineTeacherDTO> detachDiscipline(@RequestBody GroupDisciplineRequestDTO groupDisciplineRequestDTO) {
        return new BaseResponse<GroupDisciplineTeacherDTO>().success(groupService.detachDiscipline(groupDisciplineRequestDTO), "OK");
    }

    @DeleteMapping("/detach-student")
    public BaseResponse<StudentShortInfoDTO> detachStudent(@RequestParam(name = "group_id") Integer group_id,
                                                           @RequestParam(name = "student_id") String student_id) {
        return new BaseResponse<StudentShortInfoDTO>().success(groupService.detachStudent(student_id, group_id), "OK");
    }

    @DeleteMapping("delete-group")
    public BaseResponse<String> deleteGroup(@RequestParam(name = "group_id") Integer group_id) {
        return new BaseResponse<String>().success(groupService.deleteGroup(group_id), "OK");
    }

}