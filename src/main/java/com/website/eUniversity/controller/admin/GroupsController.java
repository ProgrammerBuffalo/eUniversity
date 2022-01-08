package com.website.eUniversity.controller.admin;

import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.entity.*;
import com.website.eUniversity.service.IGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-panel/groups")
@Api(value = "Groups controller of admin panel", description = "Designed to work with groups. \nController only works with admin's tokens.")
public class GroupsController {

    @Autowired
    private IGroupService groupService;

//dont remove maybe use later
//    @GetMapping("/get-students-group")
//    @ApiOperation("Returns students of requested group")
//    public ResponseEntity<BaseResponse<List<StudentShortInfoDTO>>> getStudentsGroup(@RequestParam(name = "groupId") Integer groupId) {
//        return ResponseEntity.ok(new BaseResponse<List<StudentShortInfoDTO>>().success(groupService.getAllStudents(groupId), "Students are returned"));
//    }

    @GetMapping("/get-students-by-group")
    @ApiOperation("Returns students of requested group")
    public ResponseEntity<BaseResponse<List<StudentDTO>>> getStudentsByGroup(@RequestParam(name = "groupId") Integer groupId) {
        return ResponseEntity.ok(new BaseResponse<List<StudentDTO>>().success(groupService.getStudentsByGroup(groupId), "Students are returned"));
    }

    @GetMapping("/get-all-groups")
    @ApiOperation("Returns all groups with discipline and teacher")
    public ResponseEntity<BaseResponse<List<GroupDisciplineTeacherDTO>>> getAllGroups() {
        return ResponseEntity.ok(new BaseResponse<List<GroupDisciplineTeacherDTO>>().success(groupService.getAllGroupDisciplineTeacher(), "Ok"));
    }

    @GetMapping("/get-all-groups-ddl")
    @ApiOperation("Returns all groups for dropdown")
    public ResponseEntity<BaseResponse<List<DDLResponseDTO<Integer>>>> getAllGroupsDDL() {
        return ResponseEntity.ok(new BaseResponse<List<DDLResponseDTO<Integer>>>().success(groupService.getGroupsDDL(), "Ok"));
    }

   @PostMapping("/attach-student")
   @ApiOperation("Add requested student to requested group")
   public ResponseEntity<BaseResponse<StudentShortInfoDTO>> attachStudent(@RequestParam(name = "groupId") Integer groupId,
                                                                          @RequestParam(name = "studentId") String studentId) {
       return ResponseEntity.ok(new BaseResponse<StudentShortInfoDTO>().success(groupService.attachStudent(studentId, groupId), "OK"));
   }

    @PostMapping("/add-group")
    @ApiOperation("Add new group")
    public ResponseEntity<BaseResponse<String>> addGroup(@RequestParam(name = "groupName") String groupName) {
        return ResponseEntity.ok(new BaseResponse<String>().success(groupService.addGroup(groupName), "OK"));
    }

    @PostMapping("/attach-discipline")
    @ApiOperation("Add discipline and teacher to requested group")
    public ResponseEntity<BaseResponse<GroupDisciplineTeacherDTO>> attachDiscipline(@RequestBody GroupDisciplineRequestDTO groupDisciplineRequestDTO) {
        return ResponseEntity.ok(new BaseResponse<GroupDisciplineTeacherDTO>().success(groupService.attachDiscipline(groupDisciplineRequestDTO), "OK"));
    }

    @PutMapping("/edit-group-info")
    @ApiOperation("Edits group's info")
    public ResponseEntity<BaseResponse<String>> editGroup(@RequestParam(name = "groupId") Integer groupId,
                                                          @RequestParam(name = "groupName") String groupName) {
        return ResponseEntity.ok(new BaseResponse<String>().success(groupService.editGroup(groupId, groupName), "OK"));
    }

    @DeleteMapping("/detach-discipline")
    @ApiOperation("Remove discipline and teacher from requested group")
    public ResponseEntity<BaseResponse<GroupDisciplineTeacherDTO>> detachDiscipline(@RequestBody GroupDisciplineRequestDTO groupDisciplineRequestDTO) {
        return ResponseEntity.ok(new BaseResponse<GroupDisciplineTeacherDTO>().success(groupService.detachDiscipline(groupDisciplineRequestDTO), "OK"));
    }

    @DeleteMapping("/detach-student")
    @ApiOperation("Remove student from requested group")
    public ResponseEntity<BaseResponse<StudentShortInfoDTO>> detachStudent(@RequestParam(name = "groupId") Integer groupId,
                                                                           @RequestParam(name = "studentId") String studentId) {
        return ResponseEntity.ok(new BaseResponse<StudentShortInfoDTO>().success(groupService.detachStudent(studentId, groupId), "OK"));
    }

    @DeleteMapping("/delete-group")
    @ApiOperation("Deletes group")
    public ResponseEntity<BaseResponse<String>> deleteGroup(@RequestParam(name = "groupId") Integer groupId) {
        return ResponseEntity.ok(new BaseResponse<String>().success(groupService.deleteGroup(groupId), "OK"));
    }

}