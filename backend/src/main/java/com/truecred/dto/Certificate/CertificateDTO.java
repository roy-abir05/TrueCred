package com.truecred.dto.Certificate;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class CertificateDTO {

    private UUID id;

    private UUID studentId;

    private UUID institutionId;

    private String cid;

    private String title;

    private String description;

    private String studentAddress;

    private String institutionAddress;

    private boolean revoked;

    private Instant createdAt;

    private Instant updatedAt;
}
