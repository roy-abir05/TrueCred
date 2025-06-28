package com.truecred.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.StudentWithCertificatesDTO;
import com.truecred.entity.Student;

@Mapper(componentModel = "spring", uses = CertificateMapper.class)
public interface StudentWithCertificateMapper {
    
    StudentWithCertificatesDTO toDTO(Student student);

    @Mapping(target = "password", ignore = true)
    Student toEntity(StudentWithCertificatesDTO studentWithCertificatesDTO);
}
