package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.MaterialRequestDTO;
import com.website.eUniversity.model.dto.entity.MaterialResponseDTO;
import com.website.eUniversity.model.entity.*;
import com.website.eUniversity.repository.*;
import com.website.eUniversity.service.IFileService;
import com.website.eUniversity.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IStudentMaterialRepository studentMaterialRepository;

    @Override
    public List<MaterialResponseDTO> getEducationalMaterials(Integer groupId, Integer disciplineId) throws NotFoundException {
        GroupDiscipline groupDiscipline =
                groupDisciplineRepository.findByGroup_IdAndDiscipline_Id(groupId, disciplineId)
                .orElseThrow(() -> new NotFoundException("Group or discipline not found"));

        return materialRepository.findAllByGroupDiscipline(groupDiscipline)
                .stream()
                .map(Material::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialResponseDTO> getFilesPostedByStudent(Integer groupId, Integer disciplineId, Integer studentId) throws NotFoundException {
        GroupDiscipline groupDiscipline =
                groupDisciplineRepository.findByGroup_IdAndDiscipline_Id(groupId, disciplineId)
                        .orElseThrow(() -> new NotFoundException("Group or discipline not found"));

        return materialRepository.findAllStudentMaterials(studentId, groupDiscipline)
                .stream()
                .map(Material::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MaterialResponseDTO uploadMaterial(MaterialRequestDTO materialRequestDTO) throws NotFoundException, IOException {
        GroupDiscipline groupDiscipline = groupDisciplineRepository
                .findByGroup_IdAndDiscipline_Id(materialRequestDTO.getGroupId(),
                                                materialRequestDTO.getDisciplineId())
                .orElseThrow(() -> new NotFoundException("Group, Discipline or Teacher not found"));

        EducationalProcess educationalProcess = educationalProcessRepository
                .findById(materialRequestDTO.getEducationalProcessId())
                .orElseThrow(() -> new NotFoundException("Educational process id not found"));

        Account account = accountRepository.findAccountById(materialRequestDTO.getAccountId())
                .orElseThrow(() -> new NotFoundException("Account not found"));

        File file = fileService.uploadFile(materialRequestDTO.getMultipartFile());

        Material material = materialRepository.save(
                new Material().setOrder(materialRequestDTO.getOrder())
                        .setDescription(materialRequestDTO.getDescription())
                        .setFile(file)
                        .setGroupDiscipline(groupDiscipline)
                        .setEducationalProcess(educationalProcess)
                        .setUserId(account.getId()));

        studentRepository.findByAccount(account)
                .ifPresent(student -> {
                    studentMaterialRepository.save(new StudentMaterial()
                            .setStudent(student)
                            .setMaterial(material));
                }
        );

        return Material.toDTO(material);
    }

    @Override
    public ByteArrayResource downloadMaterial(Integer materialId) throws NotFoundException, IOException {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new NotFoundException("Material not found"));

        return fileService.downloadFile(material.getFile());
    }

    @Override
    @Transactional
    public MaterialResponseDTO deleteFile(Integer materialId) throws NotFoundException {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new NotFoundException("Material not found"));

        MaterialResponseDTO materialResponseDTO = Material.toDTO(material);

        materialRepository.delete(material);

        return materialResponseDTO;
    }
}
