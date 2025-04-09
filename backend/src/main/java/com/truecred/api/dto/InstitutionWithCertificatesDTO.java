package com.truecred.api.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class InstitutionWithCertificatesDTO {

    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private String websiteUrl;

    private String logoUrl;

    private String address;

    private String description;

    private String walletAddress;

    private List<CertificateDTO> issuedCertificates;
}
