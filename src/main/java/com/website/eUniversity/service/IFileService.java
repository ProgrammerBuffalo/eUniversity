package com.website.eUniversity.service;

import com.website.eUniversity.model.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

    File uploadFile(MultipartFile multipartFile) throws IOException;

    File downloadFile(String fullPath);
}
