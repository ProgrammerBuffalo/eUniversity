package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.ThemeDTO;
import com.website.eUniversity.model.entity.GroupDiscipline;
import com.website.eUniversity.model.entity.Theme;
import com.website.eUniversity.repository.IGroupDisciplineRepository;
import com.website.eUniversity.repository.IMaterialRepository;
import com.website.eUniversity.repository.IThemeRepository;
import com.website.eUniversity.service.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeService implements IThemeService {

    @Autowired
    private IThemeRepository themeRepository;

    @Autowired
    private IGroupDisciplineRepository groupDisciplineRepository;

    @Autowired
    private IMaterialRepository materialRepository;

    @Override
    public List<ThemeDTO> getThemes(Integer groupId, Integer disciplineId) throws NotFoundException {
        GroupDiscipline groupDiscipline = groupDisciplineRepository
                .findByGroup_IdAndDiscipline_Id(groupId, disciplineId)
                .orElseThrow(() -> new NotFoundException("Group, discipline or teacher not found"));

        return themeRepository.findAllByGroupDiscipline(groupDiscipline).stream()
                .map(Theme::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ThemeDTO addTheme(ThemeDTO themeDTO) throws NotFoundException {

        GroupDiscipline groupDiscipline = groupDisciplineRepository
                .findByGroup_IdAndDiscipline_Id(themeDTO.getGroupId(), themeDTO.getDisciplineId())
                .orElseThrow(() -> new NotFoundException("Group, discipline or teacher not found"));

        return Theme.toDTO(themeRepository.save(new Theme()
                .setName(themeDTO.getThemeName())
                .setOrder(themeDTO.getOrder())
                .setGroupDiscipline(groupDiscipline)));
    }

    @Override
    public ThemeDTO deleteTheme(Integer themeId) throws NotFoundException {
        ThemeDTO themeDTO = Theme.toDTO(themeRepository.findById(themeId)
                .orElseThrow(() -> new NotFoundException("Theme not found")));

        themeRepository.deleteById(themeId);

        return themeDTO;
    }

    @Override
    public ThemeDTO attachMaterialToTheme(Integer themeId, Integer materialId) throws NotFoundException {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NotFoundException("Theme not found"));

        theme.setMaterial(materialRepository.findById(materialId)
                .orElseThrow(() -> new NotFoundException("Material not found")));

        return Theme.toDTO(themeRepository.save(theme));
    }

    @Override
    public ThemeDTO detachMaterialFromTheme(Integer themeId) throws NotFoundException {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NotFoundException("Theme not found"));

        return Theme.toDTO(themeRepository.save(theme.setMaterial(null)));
    }

    @Override
    public List<DDLResponseDTO<Integer>> getMaterialsForThemesDDL(Integer groupId, Integer disciplineId) {
        return groupDisciplineRepository.getGroupMaterialsDDL(groupId, disciplineId);
    }
}
