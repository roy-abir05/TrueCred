package com.truecred.dto.student;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.truecred.dto.walletAddress.WalletAddressDTO;

import lombok.Data;

@Data
public class StudentDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String countryCode;

    private String phoneNumber;

    private String profileImageUrl;

    private boolean approved;

    private List<WalletAddressDTO> walletAddresses;

    private Instant createdAt;

    private Instant updatedAt;
}
