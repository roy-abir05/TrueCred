package com.truecred.dto.institution;

import java.util.List;

import com.truecred.dto.walletAddress.WalletAddressDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
public class InstitutionRegistrationDTO {
    
    @NotBlank(message = "Institution name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Institution Password is required")
    private String password;

    @Size(min = 2, max = 7)
    private String countryCode = "+91";

    @Size(min = 3, max = 17)
    private String phoneNumber;

    private String websiteUrl;

    private String logoUrl;

    @NotBlank(message = "Institution Geographical Location is required")
    @Size(max = 255, message = "Institution Geographical Location must not exceed 255 characters")
    private String location;

    @NotBlank(message = "Institution About is required")
    @Size(max = 1000, message = "Institution About must not exceed 1000 characters")
    private String about;

    @ToString.Exclude
    @Valid
    private List<WalletAddressDTO> walletAddresses;
}
