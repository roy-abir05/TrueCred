package com.truecred.entity;

import com.truecred.entity.enums.AuditAction;
import com.truecred.entity.enums.AuditActor;
import com.truecred.entity.enums.AuditTarget;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "audit_logs")
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditAction action;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditTarget target;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditActor actor;

    @Column(nullable = false)
    private String actorAddress;

    @Column(nullable = false)
    private String targetAddress;

    @Column(nullable = false)
    private UUID targetId;

    @Column(columnDefinition = "TEXT")
    @Size(max = 1000)
    private String description;

    @Column(columnDefinition = "jsonb")
    @Size(max = 10000)
    private String metadata;

    @Column(nullable = false)
    private Instant createdAt;

    @PrePersist
    private void onCreate() {
        this.createdAt = Instant.now();
    }
}
