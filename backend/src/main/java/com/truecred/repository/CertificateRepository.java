package com.truecred.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truecred.entity.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, UUID> {
    
    List<Certificate> findByStudentId(UUID studentId);
    List<Certificate> findByInstitutionId(UUID institutionId);
}