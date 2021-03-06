package com.website.eUniversity.controller.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.PaginationDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.MaterialRequestDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.MaterialResponseDTO;
import com.website.eUniversity.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("student-panel/discipline")
public class StudentDisciplineController {

    @Autowired
    private IMaterialService materialService;

    @PostMapping("/materials/upload")
    public ResponseEntity<BaseResponse<?>> uploadFile(@ModelAttribute MaterialRequestDTO materialRequestDTO)
            throws NotFoundException, IOException {
        return ResponseEntity.ok(new BaseResponse<>().success(materialService.uploadMaterial(materialRequestDTO), "Material is uploaded"));
    }

    @GetMapping(value = "/materials/educational")
    public ResponseEntity<BaseResponse<?>> getMaterials(@RequestParam("groupId") Integer groupId,
                                                        @RequestParam("disciplineId") Integer disciplineId,
                                                        @RequestParam("pagination") String pagination) throws NotFoundException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        PaginationDTO paginationDTO = mapper.readValue(pagination, PaginationDTO.class);
        return ResponseEntity.ok(new BaseResponse<PaginatedListDTO<MaterialResponseDTO>>().success(materialService.getEducationalMaterials(groupId, disciplineId, paginationDTO), "Material are found"));
    }
}
