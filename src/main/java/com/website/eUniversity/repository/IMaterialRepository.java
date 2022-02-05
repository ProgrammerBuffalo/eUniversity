package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaterialRepository extends JpaRepository<Material, Integer> {
}
