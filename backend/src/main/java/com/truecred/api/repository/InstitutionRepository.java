package com.truecred.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truecred.api.entity.Institution;
import com.truecred.api.entity.Student;
import java.util.Optional;

public interface InstitutionRepository extends JpaRepository<Institution, UUID> {

    Optional<Student> findByEmail(String email);
}
