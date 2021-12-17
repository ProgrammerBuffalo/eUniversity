package com.website.eUniversity.service;


import com.website.eUniversity.model.dto.TeacherDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;

import java.util.List;

public interface ITeacherService  {
    TeacherDTO register(RegistrationDTO registrationDTO);

    void delete(String uuid);

    List<TeacherDTO> getTeacherList();
}
