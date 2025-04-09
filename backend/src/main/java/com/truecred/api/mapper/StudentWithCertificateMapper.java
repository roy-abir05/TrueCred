package com.truecred.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.api.dto.StudentWithCertificatesDTO;
import com.truecred.api.entity.Student;

@Mapper(componentModel = "spring", uses = CertificateMapper.class)
public interface StudentWithCertificateMapper {
    
    StudentWithCertificatesDTO toDTO(Student student);

    @Mapping(target = "password", ignore = true)
    Student toEntity(StudentWithCertificatesDTO studentWithCertificatesDTO);
}
