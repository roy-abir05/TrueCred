package com.truecred.api.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class InstitutionDTO {
    
    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private String websiteUrl;

    private String logoUrl;

    private String address;

    private String description;

    private String walletAddress;
}
