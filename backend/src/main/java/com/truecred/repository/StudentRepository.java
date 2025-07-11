package com.truecred.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truecred.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findByEmail(String email);
}
