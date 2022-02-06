package com.website.eUniversity.controller.admin;

import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.IDDLResponseDTO;
import com.website.eUniversity.model.dto.entity.discipline.AddDisciplineDTO;
import com.website.eUniversity.model.dto.entity.discipline.UpdateDisciplineDTO;
import com.website.eUniversity.model.dto.entity.teacher_discipline.AttachDisciplineDTO;
import com.website.eUniversity.model.dto.entity.teacher_discipline.ITeacherDisciplineDTO;
import com.website.eUniversity.model.dto.entity.teacher_discipline.ITeacherShortDisciplinesDTO;
import com.website.eUniversity.model.entity.Discipline;
import com.website.eUniversity.service.IDisciplineService;
import com.website.eUniversity.service.ITeacherDisciplineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-panel/discipline")
@Api(value = "Disciplines controller of admin panel", description = "Designed to work with disciplines. \nController only works with admin's tokens.")
public class DisciplineController {

    @Autowired
    private IDisciplineService disciplineService;

    @Autowired
    private ITeacherDisciplineService teacherDisciplineService;

    @PostMapping("/add-discipline")
    @ApiOperation("adds new discipline")
    public ResponseEntity<BaseResponse<Integer>> addDiscipline(@RequestBody AddDisciplineDTO dto) {
        try {
            return ResponseEntity.ok(new BaseResponse<Integer>().success(disciplineService.save(dto), "discipline is added"));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BaseResponse().error("such discipline already exists", 400));
        }
    }

    @GetMapping("/get-all-disciplines")
    @ApiOperation("gets all disciplines")
    public ResponseEntity<BaseResponse<List<Discipline>>> getAllDisciplines() {
        return ResponseEntity.ok(new BaseResponse<List<Discipline>>().success(disciplineService.getAllDisciplines(), "list of disciplines are returned"));
    }

    @GetMapping("/get-disciplines-ddl")
    @ApiOperation("get all disciplines for drop down")
    public ResponseEntity<BaseResponse<List<DDLResponseDTO<Integer>>>> getDisciplinesDDL() {
        return ResponseEntity.ok(new BaseResponse<List<DDLResponseDTO<Integer>>>().success(disciplineService.getDisciplinesDDL(), "list of disciplines are returned for drop down"));
    }

    @PutMapping("/update-discipline")
    @ApiOperation("updates discipline")
    public ResponseEntity<BaseResponse<Object>> updateDiscipline(@RequestBody UpdateDisciplineDTO disciplineDTO) {
        disciplineService.update(disciplineDTO);
        return ResponseEntity.ok(new BaseResponse<>().success(null, "discipline is updated"));
    }

    @DeleteMapping("/delete-discipline")
    @ApiOperation("deletes discipline")
    public ResponseEntity<BaseResponse<Object>> deleteDiscipline(@RequestParam(name = "id") Integer id) {
        disciplineService.delete(id);
        return ResponseEntity.ok(new BaseResponse<>().success(null, "discipline is deleted"));
    }

    @GetMapping("/get-teachers-short-disciplines")
    @ApiOperation("gets teachers with there discipline in one string in short format")
    public ResponseEntity<BaseResponse<List<ITeacherShortDisciplinesDTO>>> getTeachersDisciplines() {
        return ResponseEntity.ok(new BaseResponse<List<ITeacherShortDisciplinesDTO>>().success(teacherDisciplineService.getTeachersShortDisciplines(), "teachers with short disciplines are returned"));
    }

    @GetMapping("/get-teacher-disciplines")
    @ApiOperation("gets list of disciplines of the teacher")
    public ResponseEntity<BaseResponse<List<ITeacherDisciplineDTO>>> getTeacherDisciplines(@RequestParam("teacherId") Integer teacherId) {
        return ResponseEntity.ok(new BaseResponse<List<ITeacherDisciplineDTO>>().success(teacherDisciplineService.getTeacherDisciplines(teacherId), "list of disciplines of teacher"));
    }

    @GetMapping("/get-teacher-short-disciplines")
    @ApiOperation("gets a string with disciplines in short format")
    public ResponseEntity<BaseResponse<ITeacherShortDisciplinesDTO>> getTeacherShortDisciplines(@RequestParam("teacherId") Integer teacherId) {
        return ResponseEntity.ok(new BaseResponse<ITeacherShortDisciplinesDTO>().success(teacherDisciplineService.getTeacherShortDisciplines(teacherId), "string with short disciplines"));
    }

    @GetMapping("/get-discipline-teachers-ddl")
    @ApiOperation("gets list of teachers that can teach selected discipline")
    public ResponseEntity<BaseResponse<List<IDDLResponseDTO<Integer>>>> getTeacherDisciplineDDL(@RequestParam("disciplineId") Integer disciplineId) {
        return ResponseEntity.ok(new BaseResponse<List<IDDLResponseDTO<Integer>>>().success(teacherDisciplineService.getTeacherDisciplineNames(disciplineId), "list of teachers for ddl"));
    }

    @PostMapping("/attach-discipline")
    @ApiOperation("attach`s new discipline to a teacher")
    public ResponseEntity<BaseResponse<Object>> assignDiscipline(@RequestBody AttachDisciplineDTO dto) {
        try {
            teacherDisciplineService.attachDiscipline(dto);
            return ResponseEntity.ok(new BaseResponse<>().success(null, "new discipline is attached to teacher"));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BaseResponse<>().error("can`t add same discipline to teacher", 400));
        }
    }

    @DeleteMapping("/detach-discipline")
    @ApiOperation("detach`s a discipline from teacher")
    public ResponseEntity<BaseResponse<Object>> detachDiscipline(@RequestParam("teacherId") Integer teacherId,
                                                                 @RequestParam("disciplineId") Integer disciplineId) {
        teacherDisciplineService.detachDiscipline(teacherId, disciplineId);
        return ResponseEntity.ok(new BaseResponse<>().success(null, "discipline is detached from teacher"));
    }

}
