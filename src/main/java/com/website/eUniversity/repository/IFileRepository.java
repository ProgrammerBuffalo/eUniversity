package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileRepository extends JpaRepository<File, Integer> {
}
