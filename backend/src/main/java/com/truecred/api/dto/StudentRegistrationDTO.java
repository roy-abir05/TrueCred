package com.truecred.api.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class StudentRegistrationDTO {

    private UUID id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String profileImageUrl;

    private String walletAddress;
}
