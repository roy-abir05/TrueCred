package com.truecred.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truecred.entity.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, UUID> {
    
    List<Certificate> findByStudentId(UUID studentId);
    List<Certificate> findByInstitutionId(UUID institutionId);
}