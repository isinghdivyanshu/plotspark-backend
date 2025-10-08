package org.plotspark.plotsparkbackend.repository;

import org.plotspark.plotsparkbackend.model.Swimlane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SwimlaneRepo extends JpaRepository<Swimlane, UUID> {
}
