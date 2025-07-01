package com.truecred.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truecred.entity.Institution;
import com.truecred.entity.Student;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, UUID> {

    Optional<Student> findByEmail(String email);
}
