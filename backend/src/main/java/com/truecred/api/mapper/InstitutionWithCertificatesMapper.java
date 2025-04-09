package com.truecred.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.api.dto.InstitutionWithCertificatesDTO;
import com.truecred.api.entity.Institution;

@Mapper(componentModel = "spring", uses = CertificateMapper.class)
public interface InstitutionWithCertificatesMapper {

    InstitutionWithCertificatesDTO toDTO(Institution institution);

    @Mapping(target = "password", ignore = true)
    Institution toEntity(InstitutionWithCertificatesDTO institutionWithCertificatesDTO);
}
