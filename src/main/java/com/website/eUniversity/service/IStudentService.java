package com.website.eUniversity.service;

import com.website.eUniversity.model.dto.entity.StudentDTO;
import com.website.eUniversity.model.dto.identification.RegistrationDTO;
import com.website.eUniversity.service.base.IUserService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStudentService extends IUserService<StudentDTO> {

}
