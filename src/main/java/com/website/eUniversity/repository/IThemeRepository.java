package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.GroupDiscipline;
import com.website.eUniversity.model.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IThemeRepository extends JpaRepository<Theme, Integer> {

    List<Theme> findAllByGroupDiscipline(GroupDiscipline groupDiscipline);
}
