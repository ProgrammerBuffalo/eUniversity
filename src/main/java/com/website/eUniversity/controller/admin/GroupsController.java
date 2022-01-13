package com.website.eUniversity.controller.admin;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.entity.*;
import com.website.eUniversity.model.dto.entity.group.AddGroupDTO;
import com.website.eUniversity.model.dto.entity.group.UpdateGroupDTO;
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

    @GetMapping("/get-students-without-group")
    @ApiOperation("Returns all students without group")
    public ResponseEntity<BaseResponse<List<DDLResponseDTO<Integer>>>> getStudentsNoGroup() {
        return ResponseEntity.ok(new BaseResponse<List<DDLResponseDTO<Integer>>>().success(groupService.findStudentsWithoutGroup(), "Students are returned"));
    }

    @GetMapping("/get-groups")
    @ApiOperation("Returns all existing groups")
    public ResponseEntity<BaseResponse<List<GroupDTO>>> getAllGroups() {
        return ResponseEntity.ok(new BaseResponse<List<GroupDTO>>().success(groupService.getAllGroups(), "Groups successfully returned"));
    }

    @GetMapping("/get-students-by-group")
    @ApiOperation("Returns students of requested group by id")
    public ResponseEntity<BaseResponse<List<StudentDTO>>> getStudentsByGroup(@RequestParam(name = "groupId") Integer groupId) {
        return ResponseEntity.ok(new BaseResponse<List<StudentDTO>>().success(groupService.getStudentsByGroup(groupId), "Students are returned"));
    }

    @GetMapping("/get-group-teacher-discipline")
    @ApiOperation("Returns group's disciplines and teachers by id")
    public ResponseEntity<BaseResponse<List<GroupDisciplineResponseDTO>>> getGroupAllTeachersAndDisciplines(@RequestParam(name = "groupId") Integer groupId) {
        return ResponseEntity.ok(new BaseResponse<List<GroupDisciplineResponseDTO>>().success(groupService.getByGroupIdTeachersAndDisciplines(groupId), "Ok"));
    }

    @GetMapping("/get-all-groups-ddl")
    @ApiOperation("Returns all groups for dropdown")
    public ResponseEntity<BaseResponse<List<DDLResponseDTO<Integer>>>> getAllGroupsDDL() {
        return ResponseEntity.ok(new BaseResponse<List<DDLResponseDTO<Integer>>>().success(groupService.getGroupsDDL(), "Ok"));
    }

   @PostMapping("/attach-student")
   @ApiOperation("Add requested student to requested group")
   public ResponseEntity<BaseResponse<StudentShortInfoDTO>> attachStudent(@RequestParam(name = "groupId") Integer groupId,
                                                                          @RequestParam(name = "studentId") Integer studentId) {
       return ResponseEntity.ok(new BaseResponse<StudentShortInfoDTO>().success(groupService.attachStudent(studentId, groupId), "OK"));
   }

    @PostMapping("/add-group")
    @ApiOperation("Add new group")
    public ResponseEntity<BaseResponse<Integer>> addGroup(@RequestBody AddGroupDTO dto) {
        return ResponseEntity.ok(new BaseResponse<Integer>().success(groupService.addGroup(dto), "OK"));
    }

    @PostMapping("/attach-teacher-discipline")
    @ApiOperation("Add discipline and teacher to requested group")
    public ResponseEntity<BaseResponse<GroupDisciplineResponseDTO>> attachDiscipline(@RequestBody GroupDisciplineRequestDTO groupDisciplineRequestDTO) {
        return ResponseEntity.ok(new BaseResponse<GroupDisciplineResponseDTO>().success(groupService.attachDiscipline(groupDisciplineRequestDTO), "OK"));
    }

    @PutMapping("/edit-group-info")
    @ApiOperation("Edits group's info")
    public ResponseEntity<BaseResponse<GroupDTO>> editGroup(@RequestBody UpdateGroupDTO dto) {
        return ResponseEntity.ok(new BaseResponse<GroupDTO>().success(groupService.editGroup(dto), "OK"));
    }

    @DeleteMapping("/detach-teacher-discipline")
    @ApiOperation("Remove discipline and teacher from requested group")
    public ResponseEntity<BaseResponse<GroupDisciplineResponseDTO>> detachDiscipline(@RequestBody GroupDisciplineRequestDTO groupDisciplineRequestDTO) throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<GroupDisciplineResponseDTO>().success(groupService.detachDiscipline(groupDisciplineRequestDTO), "OK"));
    }

    @DeleteMapping("/detach-student")
    @ApiOperation("Remove student from requested group")
    public ResponseEntity<BaseResponse<StudentShortInfoDTO>> detachStudent(@RequestParam(name = "groupId") Integer groupId,
                                                                           @RequestParam(name = "studentId") Integer studentId) {
        return ResponseEntity.ok(new BaseResponse<StudentShortInfoDTO>().success(groupService.detachStudent(studentId, groupId), "OK"));
    }

    @DeleteMapping("/delete-group")
    @ApiOperation("Deletes group")
    public ResponseEntity<BaseResponse<Integer>> deleteGroup(@RequestParam(name = "groupId") Integer groupId) {
        return ResponseEntity.ok(new BaseResponse<Integer>().success(groupService.deleteGroup(groupId), "OK"));
    }

}