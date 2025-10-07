package org.plotspark.plotsparkbackend.repository;

import org.plotspark.plotsparkbackend.model.Canvas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CanvasRepo extends JpaRepository<Canvas, UUID> {
}
