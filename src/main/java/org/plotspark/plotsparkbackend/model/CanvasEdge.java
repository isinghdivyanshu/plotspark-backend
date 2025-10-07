package org.plotspark.plotsparkbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "canvas_edges")
public class CanvasEdge {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canvas_id", nullable = false)
    private Canvas canvas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_node_id",  nullable = false)
    private CanvasNode sourceNode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_node_id",  nullable = false)
    private CanvasNode targetNode;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EdgeType type = EdgeType.CONTINUES;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
