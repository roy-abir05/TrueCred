package com.truecred.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truecred.entity.Certificate;
import com.truecred.repository.CertificateRepository;

@Service
public class CertificateService {
    
    @Autowired
    private CertificateRepository certificateRepository;

    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    public Certificate getCertificateById(UUID id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    public List<Certificate> getCertificatesByStudentId(UUID studentId) {
        return certificateRepository.findByStudentId(studentId);
    }

    public List<Certificate> getCertificatesByInstitutionId(UUID institutionId) {
        return certificateRepository.findByInstitutionId(institutionId);
    }

    public Certificate saveCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }
}
