package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.entity.File;
import com.website.eUniversity.repository.IFileRepository;
import com.website.eUniversity.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService implements IFileService {

    @Value("${storage.uploads.directory-name}")
    private String fileStorageName;

    //private final Path storagePath = getClass().getResource();

    @Autowired
    private IFileRepository fileRepository;

    @Override
    public File uploadFile(MultipartFile multipartFile) throws IOException {
        String fileName = UUID.randomUUID().toString();
        //Files.copy(multipartFile.getInputStream(), this.storagePath.resolve(fileName));

        return null;
    }

    @Override
    public File downloadFile(String fullPath) {
        return null;
    }
}
