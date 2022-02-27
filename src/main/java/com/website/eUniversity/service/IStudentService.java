package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.admin_panel.entity.StudentDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.StudentShortInfoDTO;
import com.website.eUniversity.model.dto.student_panel.StudentInfoDTO;
import com.website.eUniversity.service.base.IUserService;

public interface IStudentService extends IUserService<StudentDTO> {

    StudentInfoDTO getStudentInfo(String accountId) throws NotFoundException;
}
