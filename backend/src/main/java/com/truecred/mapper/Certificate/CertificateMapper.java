package com.truecred.mapper.Certificate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.Certificate.CertificateDTO;
import com.truecred.entity.Certificate;

@Mapper(componentModel = "spting")
public interface CertificateMapper {
    CertificateDTO toDTO(Certificate certificate);

    @Mapping(target = "institution", ignore = true)
    @Mapping(target = "student", ignore = true)
    Certificate toEntity(CertificateDTO certificateDTO);
}
