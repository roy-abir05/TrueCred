package com.truecred.mapper.Student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.Student.StudentWithCertificatesDTO;
import com.truecred.entity.Student;
import com.truecred.mapper.Certificate.CertificateMapper;

@Mapper(componentModel = "spring", uses = CertificateMapper.class)
public interface StudentWithCertificateMapper {
    
    StudentWithCertificatesDTO toDTO(Student student);

    @Mapping(target = "password", ignore = true)
    Student toEntity(StudentWithCertificatesDTO studentWithCertificatesDTO);
}
