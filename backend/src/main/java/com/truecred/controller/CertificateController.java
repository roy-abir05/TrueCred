package com.truecred.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truecred.dto.CertificateDTO;
import com.truecred.entity.Certificate;
import com.truecred.mapper.CertificateMapper;
import com.truecred.service.CertificateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private CertificateMapper certificateMapper;

    @PostMapping
    public ResponseEntity<Void> createCertificate(@RequestBody @Valid CertificateDTO certificateDTO) {
        Certificate certificate = certificateMapper.toEntity(certificateDTO);
        certificateService.saveCertificate(certificate);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<CertificateDTO> getAllCertificates() {
        return certificateService.getAllCertificates()
                .stream()
                .map(certificateMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CertificateDTO getCertificateById(@PathVariable UUID id) {
        Certificate cert = certificateService.getCertificateById(id);
        return certificateMapper.toDTO(cert);
    }

    @GetMapping("/student/{studentId}")
    public List<CertificateDTO> getByStudent(@PathVariable UUID studentId) {
        return certificateService.getCertificatesByStudentId(studentId)
                .stream()
                .map(certificateMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/institution/{institutionId}")
    public List<CertificateDTO> getByInstitution(@PathVariable UUID institutionId) {
        return certificateService.getCertificatesByInstitutionId(institutionId)
                .stream()
                .map(certificateMapper::toDTO)
                .collect(Collectors.toList());
    }
}
