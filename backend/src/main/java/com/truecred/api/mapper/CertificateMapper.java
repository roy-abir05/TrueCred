package com.truecred.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.api.dto.CertificateDTO;
import com.truecred.api.entity.Certificate;

@Mapper(componentModel = "spting")
public interface CertificateMapper {
    CertificateDTO toDTO(Certificate certificate);

    @Mapping(target = "institution", ignore = true)
    @Mapping(target = "student", ignore = true)
    Certificate toEntity(CertificateDTO certificateDTO);
}
