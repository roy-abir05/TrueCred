package com.truecred.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "institutions",
        indexes = {
                @Index(columnList = "email"),
                @Index(columnList = "approved")
        }
)
@Data
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "Institution name is required")
    private String name;

    @Column(unique = true, nullable = false)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Institution Password is required")
    private String password;

    @Column(nullable = false)
    @Size(min = 2, max = 7)
    private String countryCode = "+91";

    @Column(nullable = false)
    @Size(min = 3, max = 17)
    private String phoneNumber;

    private String websiteUrl;

    private String logoUrl;

    @Column(nullable = false)
    @NotBlank(message = "Institution Geographical Location is required")
    @Size(max = 255, message = "Institution Geographical Location must not exceed 255 characters")
    private String location;

    @Column(nullable = false)
    @NotBlank(message = "Institution About is required")
    @Size(max = 1000, message = "Institution About must not exceed 1000 characters")
    private String about;

    @Column(nullable = false)
    private boolean approved;

    @ToString.Exclude
    @OneToMany(mappedBy = "institution")
    private List<WalletAddress> walletAddresses;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    private void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
        approved = false;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
