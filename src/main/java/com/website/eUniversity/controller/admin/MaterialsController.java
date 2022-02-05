package com.website.eUniversity.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.entity.MaterialRequestDTO;
import com.website.eUniversity.model.dto.entity.MaterialResponseDTO;
import com.website.eUniversity.service.IMaterialService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin-panel/materials")
public class MaterialsController {

    @Autowired
    private IMaterialService materialService;

    @GetMapping(value = "/educational")
    public ResponseEntity<BaseResponse<?>> getMaterials(@RequestParam Integer groupId,
                                                        @RequestParam Integer disciplineId) throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<List<MaterialResponseDTO>>().success(materialService.getEducationalMaterials(groupId, disciplineId), "Material are found"));
    }

    @GetMapping(value = "/posted-by-student")
    public ResponseEntity<BaseResponse<?>> getMaterials(@RequestParam Integer groupId,
                                                        @RequestParam Integer disciplineId,
                                                        @RequestParam Integer studentId) throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<List<MaterialResponseDTO>>().success(materialService.getFilesPostedByStudent(groupId, disciplineId, studentId), "Material are found"));
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
