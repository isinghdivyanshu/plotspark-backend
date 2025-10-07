package org.plotspark.plotsparkbackend.repository;

import org.plotspark.plotsparkbackend.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, UUID> {
}
