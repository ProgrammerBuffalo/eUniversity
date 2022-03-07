package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.MaterialResponseDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.ThemeDTO;

import java.util.List;

public interface IThemeService {

    List<ThemeDTO> getThemes(Integer groupId, Integer disciplineId) throws NotFoundException;

    List<DDLResponseDTO<Integer>> getMaterialsForThemesDDL(Integer groupId, Integer disciplineId);

    ThemeDTO addTheme(ThemeDTO themeDTO) throws NotFoundException;

    ThemeDTO deleteTheme(Integer themeId) throws NotFoundException;

    ThemeDTO attachMaterialToTheme(Integer themeId, Integer materialId) throws NotFoundException;

    ThemeDTO detachMaterialFromTheme(Integer themeId) throws NotFoundException;
}
