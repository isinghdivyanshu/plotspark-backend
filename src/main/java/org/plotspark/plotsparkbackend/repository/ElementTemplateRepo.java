package org.plotspark.plotsparkbackend.repository;

import org.plotspark.plotsparkbackend.model.ElementTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ElementTemplateRepo extends JpaRepository<ElementTemplate, UUID> {
}
