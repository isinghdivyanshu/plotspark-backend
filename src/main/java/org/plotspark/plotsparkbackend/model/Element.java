package org.plotspark.plotsparkbackend.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "elements", uniqueConstraints = {@UniqueConstraint(columnNames = {"project_id", "element_type", "name"})})
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "element_type", nullable = false)
    private String elementType;

    @Column(nullable = false)
    private String name;

    @Type(JsonType.class)
    @Column(name = "element_details", columnDefinition = "jsonb")
    private Map<String, Object> elementDetails;

    @Type(JsonType.class)
    @Column(name = "custom_fields", columnDefinition = "jsonb")
    private Map<String, Object> customFields;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "element")
    private List<CanvasNode> canvasNodes = new ArrayList<>();
}
