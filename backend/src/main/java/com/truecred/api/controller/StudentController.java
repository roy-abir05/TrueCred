package com.truecred.api.controller;

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

import com.truecred.api.dto.StudentDTO;
import com.truecred.api.dto.StudentRegistrationDTO;
import com.truecred.api.entity.Student;
import com.truecred.api.mapper.StudentMapper;
import com.truecred.api.mapper.StudentRegistrationMapper;
import com.truecred.api.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentRegistrationMapper studentRegistrationMapper;

    @PostMapping("/register")
    public ResponseEntity<Void> registerStudent(@RequestBody @Valid StudentRegistrationDTO registrationDTO) {
        Student student = studentRegistrationMapper.toEntity(registrationDTO);
        studentService.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable UUID id) {
        Student student = studentService.getStudentById(id);
        return studentMapper.toDTO(student);
    }
}
