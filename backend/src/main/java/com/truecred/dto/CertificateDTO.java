package com.truecred.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class CertificateDTO {

    private UUID id;

    @NotBlank(message = "Certificate CID (IPFS hash) is required")
    private String certificateCid;

    @NotBlank(message = "Certificate title is required")
    private String certificateTitle;

    @PastOrPresent(message = "Issue date cannot be in the future")
    private LocalDate issueDate;
}
