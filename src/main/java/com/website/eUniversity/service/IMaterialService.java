package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.MaterialRequestDTO;
import com.website.eUniversity.model.dto.entity.MaterialResponseDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IMaterialService {

    List<MaterialResponseDTO> getEducationalMaterials(Integer groupId, Integer disciplineId);

    List<MaterialResponseDTO> getFilesPostedByStudent(Integer groupId, Integer disciplineId, Integer studentId);

    MaterialResponseDTO uploadMaterial(MaterialRequestDTO materialRequestDTO) throws NotFoundException, IOException;

    ByteArrayResource downloadMaterial(Integer materialId) throws NotFoundException, IOException;

    MaterialResponseDTO deleteFile(Integer materialId);
}
