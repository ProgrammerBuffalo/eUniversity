package com.website.eUniversity.controller.admin;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.base.BaseResponse;
import com.website.eUniversity.model.dto.DDLResponseDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.ScheduleDisciplineDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.ThemeDTO;
import com.website.eUniversity.model.entity.Theme;
import com.website.eUniversity.service.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-panel/themes")
public class AdminThemeController {

    @Autowired
    private IThemeService themeService;

    @GetMapping("/get-themes")
    public ResponseEntity<BaseResponse<?>> getGroupScheduleLessons(@RequestParam("groupId") Integer groupId,
                                                                   @RequestParam("disciplineId") Integer disciplineId)
            throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<List<ThemeDTO>>().success(themeService.getThemes(groupId, disciplineId), "OK"));
    }

    @GetMapping("/get-materials-for-theme")
    public ResponseEntity<BaseResponse<?>> getMaterialsForTheme(@RequestParam("groupId") Integer groupId,
                                                                @RequestParam("disciplineId") Integer disciplineId) {
        return ResponseEntity.ok(new BaseResponse<List<DDLResponseDTO<Integer>>>().success(themeService.getMaterialsForThemesDDL(groupId, disciplineId), "OK"));
    }

    @PutMapping("/attach-material")
    public ResponseEntity<BaseResponse<?>> attachMaterial(@RequestParam("themeId") Integer themeId,
                                                          @RequestParam("materialId") Integer materialId) throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<ThemeDTO>().success(themeService.attachMaterialToTheme(themeId, materialId), "OK"));
    }

    @PutMapping("/detach-material")
    public ResponseEntity<BaseResponse<?>> detachMaterial(@RequestParam("themeId") Integer themeId)
            throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<ThemeDTO>().success(themeService.detachMaterialFromTheme(themeId), "OK"));
    }

    @PostMapping("/add-theme")
    public ResponseEntity<BaseResponse<?>> addTheme(@RequestBody ThemeDTO themeDTO) throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<ThemeDTO>().success(themeService.addTheme(themeDTO), "OK"));
    }

    @DeleteMapping("/remove-theme")
    public ResponseEntity<BaseResponse<?>> removeTheme(@RequestParam("themeId") Integer themeId) throws NotFoundException {
        return ResponseEntity.ok(new BaseResponse<ThemeDTO>().success(themeService.deleteTheme(themeId), "OK"));
    }

}
