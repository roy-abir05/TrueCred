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

import com.truecred.dto.institution.InstitutionDTO;
import com.truecred.dto.institution.InstitutionRegistrationDTO;
import com.truecred.entity.Institution;
import com.truecred.mapper.Institution.InstitutionMapper;
import com.truecred.mapper.Institution.InstitutionRegistrationMapper;
import com.truecred.service.InstitutionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/institutions")
public class InstitutionController {
    
    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private InstitutionMapper institutionMapper;

    @Autowired
    private InstitutionRegistrationMapper institutionRegistrationMapper;

    @PostMapping("/register")
    public ResponseEntity<Void> registerInstitution(@RequestBody @Valid InstitutionRegistrationDTO registrationDTO) {
        Institution institution = institutionRegistrationMapper.toEntity(registrationDTO);
        institutionService.saveInstitution(institution);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<InstitutionDTO> getAllInstitutions() {
        return institutionService.getAllInstitutions()
                .stream()
                .map(institutionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InstitutionDTO getInstitutionById(@PathVariable UUID id) {
        Institution institution = institutionService.getInstitutionById(id);
        return institutionMapper.toDTO(institution);
    }

}
