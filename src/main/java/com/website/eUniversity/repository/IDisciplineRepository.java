package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDisciplineRepository extends JpaRepository<Discipline, Integer> {
}
