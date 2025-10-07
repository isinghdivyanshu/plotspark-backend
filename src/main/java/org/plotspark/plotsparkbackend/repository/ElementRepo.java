package org.plotspark.plotsparkbackend.repository;

import org.plotspark.plotsparkbackend.model.Element;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ElementRepo extends JpaRepository<Element, UUID> {
}
