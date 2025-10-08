package org.plotspark.plotsparkbackend.repository;

import org.plotspark.plotsparkbackend.model.CanvasEdge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CanvasEdgeRepo extends JpaRepository<CanvasEdge, UUID> {
}
