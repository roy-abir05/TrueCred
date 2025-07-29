package com.truecred.mapper.Certificate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.Certificate.CertificateDTO;
import com.truecred.entity.Certificate;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    @Mappings({
            @Mapping(source = "student.id", target = "studentId"),
            @Mapping(source = "institution.id", target = "institutionId")
    })
    CertificateDTO toDTO(Certificate certificate);

    @Mappings({
            @Mapping(target = "student", ignore = true),
            @Mapping(target = "institution", ignore = true)
    })
    Certificate toEntity(CertificateDTO certificateDTO);
}
