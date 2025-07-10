package com.truecred.dto.institution;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.truecred.dto.walletAddress.WalletAddressDTO;

import lombok.Data;
import lombok.ToString;

@Data
public class InstitutionDTO {
    
    private UUID id;

    private String name;

    private String email;

    private String countryCode;

    private String phoneNumber;

    private String websiteUrl;

    private String logoUrl;

    private String location;

    private String about;

    private boolean approved;

    @ToString.Exclude
    private List<WalletAddressDTO> walletAddresses;

    private Instant createdAt;

    private Instant updatedAt;
}
