package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IThemeRepository extends JpaRepository<Theme, Integer> {
}
