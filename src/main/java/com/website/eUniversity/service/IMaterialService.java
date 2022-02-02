package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.MaterialRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IMaterialService {

    boolean uploadMaterial(MaterialRequestDTO materialRequestDTO) throws NotFoundException, IOException;

    MultipartFile downloadMaterial(Integer materialId);
}
