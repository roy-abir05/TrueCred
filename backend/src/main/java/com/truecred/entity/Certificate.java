package com.truecred.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "certificates")
@Data
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    @NotNull(message = "Student reference is required")
    private Student student;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", nullable = false)
    @NotNull(message = "Institution reference is required")
    private Institution institution;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Certificate CID is required")
    @Size(max = 255)
    private String cid;

    @Column(nullable = false)
    @NotBlank(message = "Certificate title is required")
    @Size(max = 50)
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Certificate description is required")
    @Size(min = 7, max = 255)
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "Student Wallet Address is Required")
    private String studentAddress;

    @Column(nullable = false)
    @NotBlank(message = "Institution Wallet Address is Required")
    private String institutionAddress;

    @Column(nullable = false)
    private boolean revoked = false;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
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
