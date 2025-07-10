package com.truecred.dto.student;

import java.util.List;

import com.truecred.dto.walletAddress.WalletAddressDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
public class StudentRegistrationDTO {

    @NotBlank(message = "First Name is required")
    private String firstName;

    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Country Code is required")
    private String countryCode;

    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;

    private String profileImageUrl;

    @ToString.Exclude
    @Valid
    private List<WalletAddressDTO> walletAddresses;
}
