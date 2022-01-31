package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.MaterialRequestDTO;
import com.website.eUniversity.model.entity.EducationalProcess;
import com.website.eUniversity.model.entity.File;
import com.website.eUniversity.model.entity.GroupDiscipline;
import com.website.eUniversity.model.entity.Material;
import com.website.eUniversity.repository.IEducationalProcessRepository;
import com.website.eUniversity.repository.IGroupDisciplineRepository;
import com.website.eUniversity.repository.IMaterialRepository;
import com.website.eUniversity.service.IFileService;
import com.website.eUniversity.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class MaterialService implements IMaterialService {

    @Autowired
    private IMaterialRepository materialRepository;

    @Autowired
    private IGroupDisciplineRepository groupDisciplineRepository;

    @Autowired
    private IEducationalProcessRepository educationalProcessRepository;

    @Autowired
    private IFileService fileService;

    @Override
    @Transactional
    public boolean uploadMaterial(MaterialRequestDTO materialRequestDTO) throws NotFoundException, IOException {
        Optional<GroupDiscipline> groupDiscipline = groupDisciplineRepository.findByGroup_IdAndDiscipline_IdAndTeacher_Id(
                materialRequestDTO.getGroupDisciplineRequestDTO().getGroupId(),
                materialRequestDTO.getGroupDisciplineRequestDTO().getDisciplineId(),
                materialRequestDTO.getGroupDisciplineRequestDTO().getTeacherId());

        if(!groupDiscipline.isPresent())
            throw new NotFoundException("Group, Discipline or Teacher not found");

        Optional<EducationalProcess> educationalProcess = educationalProcessRepository.findById(materialRequestDTO.getEducationalProcessId());

        if(!educationalProcess.isPresent())
            throw new NotFoundException("Educational process id not found");

        File file = fileService.uploadFile(materialRequestDTO.getMultipartFile());

        Material material = materialRepository.save(
                new Material().setOrder(materialRequestDTO.getOrder())
                        .setFile(file)
                        .setGroupDiscipline(groupDiscipline.get())
                        .setEducationalProcess(educationalProcess.get()));

        return true;
    }

    @Override
    public MultipartFile downloadMaterial(Integer materialId) {
        return null;
    }
}
