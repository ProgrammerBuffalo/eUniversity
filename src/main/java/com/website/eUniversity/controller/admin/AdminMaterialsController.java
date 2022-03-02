package com.website.eUniversity.controller.admin;

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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin-panel/materials")
public class AdminMaterialsController {

    @Autowired
    private IMaterialService materialService;

    @GetMapping(value = "/educational")
    public ResponseEntity<BaseResponse<PaginatedListDTO<MaterialResponseDTO>>> getMaterials(@RequestParam("groupId") Integer groupId,
                                                                                            @RequestParam("disciplineId") Integer disciplineId,
                                                                                            @RequestParam("pagination") String pagination)
            throws NotFoundException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        PaginationDTO paginationDTO = mapper.readValue(pagination, PaginationDTO.class);
        return ResponseEntity.ok(new BaseResponse<PaginatedListDTO<MaterialResponseDTO>>().success(materialService.getEducationalMaterials(groupId, disciplineId, paginationDTO), "Material are found"));
    }

    @GetMapping(value = "/posted-by-student")
    public ResponseEntity<BaseResponse<PaginatedListDTO<MaterialResponseDTO>>> getMaterials(@RequestParam("groupId") Integer groupId,
                                                                                            @RequestParam("disciplineId") Integer disciplineId,
                                                                                            @RequestParam("studentId") Integer studentId,
                                                                                            @RequestParam("pagination") String pagination)
            throws NotFoundException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        PaginationDTO paginationDTO = mapper.readValue(pagination, PaginationDTO.class);
        return ResponseEntity.ok(new BaseResponse<PaginatedListDTO<MaterialResponseDTO>>().success(materialService.getFilesPostedByStudent(groupId, disciplineId, studentId, paginationDTO), "Material are found"));
    }

    @GetMapping(value = "/download-file")
    public ResponseEntity<BaseResponse<?>> download(@RequestParam Integer materialId)
            throws NotFoundException, IOException {
        ByteArrayResource byteArrayResource = materialService.downloadMaterial(materialId);
        return ResponseEntity
                .ok()
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new BaseResponse<>().success(byteArrayResource, "File successfully returned"));

    }

    @PostMapping(value = "/upload-file")
    public ResponseEntity<BaseResponse<?>> upload(@ModelAttribute MaterialRequestDTO materialRequestDTO)
            throws NotFoundException, IOException {
        return ResponseEntity.ok(new BaseResponse<>().success(materialService.uploadMaterial(materialRequestDTO), "File uploaded successfully"));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<BaseResponse<?>> delete(@RequestParam("materialId") Integer id) throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<>().success(materialService.deleteFile(id), "File is deleted"));
    }
}
