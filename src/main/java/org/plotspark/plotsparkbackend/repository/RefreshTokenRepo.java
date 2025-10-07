package org.plotspark.plotsparkbackend.repository;

import org.plotspark.plotsparkbackend.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, UUID> {
}
