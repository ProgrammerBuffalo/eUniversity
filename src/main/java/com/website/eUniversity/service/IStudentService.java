package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.StudentDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;

import java.util.List;

public interface IStudentService  {
    StudentDTO register(RegistrationDTO registrationDTO);

    void delete(String uuid);

    List<StudentDTO> getStudentList();
}
