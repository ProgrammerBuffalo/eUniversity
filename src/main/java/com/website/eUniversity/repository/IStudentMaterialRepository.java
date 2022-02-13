package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.StudentMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IStudentMaterialRepository extends JpaRepository<StudentMaterial, Integer> {

}
