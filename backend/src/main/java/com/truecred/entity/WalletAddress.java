package com.truecred.entity;

import java.time.Instant;
import java.util.UUID;

import com.truecred.entity.enums.Owner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "wallet_addresses")
@Getter
@Setter
@ToString
public class WalletAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String address;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "institution")
    private Institution institution;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private Owner entityType;

    @Column(nullable = false)
    private boolean revoked;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt = Instant.now();
}
