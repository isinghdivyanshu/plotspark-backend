package org.plotspark.plotsparkbackend.repository;

import org.plotspark.plotsparkbackend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepo extends JpaRepository<Project, UUID> {
}