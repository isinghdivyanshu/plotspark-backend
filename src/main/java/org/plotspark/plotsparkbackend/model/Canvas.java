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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "canvases", uniqueConstraints = {@UniqueConstraint(columnNames = {"project_id", "name"})})
public class Canvas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String name;

    @Type(JsonType.class)
    @Column(name = "canvas_notes", columnDefinition = "jsonb")
    private Map<String, Object> canvasNotes;

    @Type(JsonType.class)
    @Column(name = "view_states", columnDefinition = "jsonb")
    private Map<String, Object> viewStates;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "canvas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CanvasNode> canvasNodes = new ArrayList<>();

    @OneToMany(mappedBy = "canvas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Swimlane> swimlanes = new ArrayList<>();
}
