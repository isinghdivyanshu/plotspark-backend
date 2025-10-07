package org.plotspark.plotsparkbackend.repository;

import org.plotspark.plotsparkbackend.model.CanvasNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CanvasNodeRepo extends JpaRepository<CanvasNode, UUID> {
}
