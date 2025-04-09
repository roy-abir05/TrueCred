package com.truecred.api.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CertificateDTO {

    private UUID id;
    private String certificateCid;
    private String certificateTitle;
    private String issueDate;
}
