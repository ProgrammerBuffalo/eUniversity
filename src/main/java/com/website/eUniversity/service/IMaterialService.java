package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.PaginationDTO;
import com.website.eUniversity.model.dto.entity.material.AddMaterialRequestDTO;
import com.website.eUniversity.model.dto.entity.material.GetMaterialRequestDTO;
import com.website.eUniversity.model.dto.entity.material.MaterialResponseDTO;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.util.List;

public interface IMaterialService {

    PaginatedListDTO<MaterialResponseDTO> getEducationalMaterials(Integer groupId, Integer disciplineId, PaginationDTO pagination) throws NotFoundException;

    PaginatedListDTO<MaterialResponseDTO> getFilesPostedByStudent(Integer groupId, Integer disciplineId, Integer studentId, PaginationDTO pagination) throws NotFoundException;

    MaterialResponseDTO uploadMaterial(AddMaterialRequestDTO materialRequestDTO) throws NotFoundException, IOException;

    ByteArrayResource downloadMaterial(Integer materialId) throws NotFoundException, IOException;

    MaterialResponseDTO deleteFile(Integer materialId) throws NotFoundException;
}
