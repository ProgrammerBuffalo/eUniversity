package com.website.eUniversity.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin-panel/materials")
public class MaterialsController {

    @GetMapping("/test")
    public String getObj(@RequestBody MultipartFile multipartFile) {
        System.err.println(multipartFile.getName());
        System.err.println(multipartFile.getOriginalFilename());
        System.err.println(multipartFile.getSize());
        System.err.println(multipartFile.getContentType());
        System.err.println(multipartFile.isEmpty());
        return multipartFile.getName();
    }
}
