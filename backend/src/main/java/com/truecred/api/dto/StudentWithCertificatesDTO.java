package com.truecred.api.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class StudentWithCertificatesDTO {

    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;
    private String walletAddress;
    private List<CertificateDTO> certificates;
}
