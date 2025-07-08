package com.truecred.dto.walletAddress;

import java.time.Instant;

import com.truecred.entity.enums.Owner;

import lombok.Data;

@Data
public class WalletAddressDTO {

    private String address;

    private Owner entityType;

    private boolean revoked;

    private Instant createdAt;

    private Instant updatedAt;
}
