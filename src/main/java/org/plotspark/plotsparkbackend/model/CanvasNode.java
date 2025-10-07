package org.plotspark.plotsparkbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "canvas_nodes")
public class CanvasNode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canvas_id", nullable = false)
    private Canvas canvas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_id")
    private Element element;

    @Column(name = "position_x", nullable = false)
    private Double positionX;

    @Column(name = "position_y", nullable = false)
    private Double positionY;

    @Column(name = "annotation", columnDefinition = "TEXT")
    @Basic(fetch = FetchType.LAZY)
    private String annotation;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "sourceNode", cascade = CascadeType.ALL)
    private List<CanvasEdge> edgeSources = new ArrayList<>();

    @OneToMany(mappedBy = "targetNode", cascade = CascadeType.ALL)
    private List<CanvasEdge> edgeTargets = new ArrayList<>();
}