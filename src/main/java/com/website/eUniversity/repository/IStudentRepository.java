package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, String> {

    void deleteById(String id);
}
