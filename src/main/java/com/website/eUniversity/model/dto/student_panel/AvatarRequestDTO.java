package com.website.eUniversity.model.dto.student_panel;

import org.springframework.web.multipart.MultipartFile;

public class AvatarRequestDTO {

    private String accountId;

    private MultipartFile multipartFile;

    public String getAccountId() {
        return accountId;
    }

    public AvatarRequestDTO setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public AvatarRequestDTO setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
        return this;
    }
}
