package com.truecred.entity;

import java.time.Instant;
import java.util.UUID;

import com.truecred.entity.enums.Owner;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wallet_addresses",
        indexes = {
                @Index(columnList = "address"),
                @Index(columnList = "student_id"),
                @Index(columnList = "institution_id")
        }
)
@Data
public class WalletAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String address;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private Owner entityType;

    @Column(nullable = false)
    private boolean revoked;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    private void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
