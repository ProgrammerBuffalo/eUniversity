package com.website.eUniversity.service.impl;

import com.website.eUniversity.model.entity.File;
import com.website.eUniversity.repository.IFileRepository;
import com.website.eUniversity.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService implements IFileService {

    private final ResourceLoader resourceLoader;

    private final IFileRepository fileRepository;

    private final Path storagePath;

    public FileService(@Value("${storage.uploads.directory-name}") String fileStorageName,
                       IFileRepository fileRepository, ResourceLoader resourceLoader) throws IOException {
        this.fileRepository = fileRepository;
        this.resourceLoader = resourceLoader;

        storagePath = Paths.get(this.resourceLoader.getResource("classpath:" + fileStorageName).getURI());
    }

    @Override
    public File uploadFile(MultipartFile multipartFile) throws IOException {
        String fileName = UUID.randomUUID().toString();
        String extension = "." + StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        Path fullPath = this.storagePath.resolve(fileName + extension);
        Files.copy(multipartFile.getInputStream(), fullPath);

        return fileRepository.save(
                new File()
                .setFileName(fileName)
                .setFilePath(fullPath.toString())
                .setFileExtension(extension)
        );
    }

    @Override
    public ByteArrayResource downloadFile(File file) throws IOException {
        return new ByteArrayResource(
                Files.readAllBytes(Paths.get(file.getFilePath()))
        );
    }

    @Override
    public void deleteFile(File file) throws IOException {
        fileRepository.delete(file);
        Files.delete(Paths.get(file.getFilePath()));
    }

}
