package com.example.timesnap.domain.project.service;

import com.example.timesnap.domain.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findAllById(Long userid);
}