package com.website.eUniversity.controller.admin;

import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.entity.DDLResponseDTO;
import com.website.eUniversity.model.dto.entity.discipline.AddDisciplineDTO;
import com.website.eUniversity.model.dto.entity.discipline.UpdateDisciplineDTO;
import com.website.eUniversity.model.entity.Discipline;
import com.website.eUniversity.service.IDisciplineService;
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

    @PostMapping("/add-discipline")
    @ApiOperation("adds new discipline")
    public ResponseEntity<BaseResponse<Integer>> addDiscipline(@RequestBody AddDisciplineDTO dto) {
        try{
            return ResponseEntity.ok(new BaseResponse<Integer>().success(disciplineService.save(dto), "Student is added"));
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new BaseResponse().error("such discipline already exists", 400));
        }
    }

    @GetMapping("/get-all-disciplines")
    @ApiOperation("gets all disciplines")
    public ResponseEntity<BaseResponse<List<Discipline>>> getAllDisciplines() {
        return ResponseEntity.ok(new BaseResponse<List<Discipline>>().success(disciplineService.getAllDisciplines(), "get`s all disciplines"));
    }

    @GetMapping("/get-disciplines-dll")
    @ApiOperation("get all disciplines for drop down")
    public ResponseEntity<BaseResponse<List<DDLResponseDTO<Integer>>>> getDisciplinesDDL() {
        return ResponseEntity.ok(new BaseResponse<List<DDLResponseDTO<Integer>>>().success(disciplineService.getDisciplinesDDL(), "get`s all disciplines for drop down"));
    }

    @PutMapping("/update-discipline")
    @ApiOperation("updates discipline")
    public ResponseEntity<BaseResponse<Object>> updateDiscipline(@RequestBody UpdateDisciplineDTO disciplineDTO) {
        disciplineService.update(disciplineDTO);
        return ResponseEntity.ok(new BaseResponse<>().success(null, "aa"));
    }

    @DeleteMapping("/delete-discipline")
    @ApiOperation("deletes discipline")
    public ResponseEntity<BaseResponse<Object>> deleteDiscipline(@RequestParam(name = "id") Integer id) {
        disciplineService.delete(id);
        return ResponseEntity.ok(new BaseResponse<>().success(null, ""));
    }
}
