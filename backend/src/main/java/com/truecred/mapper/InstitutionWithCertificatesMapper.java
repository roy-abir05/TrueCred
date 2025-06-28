package com.truecred.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.InstitutionWithCertificatesDTO;
import com.truecred.entity.Institution;

@Mapper(componentModel = "spring", uses = CertificateMapper.class)
public interface InstitutionWithCertificatesMapper {

    InstitutionWithCertificatesDTO toDTO(Institution institution);

    @Mapping(target = "password", ignore = true)
    Institution toEntity(InstitutionWithCertificatesDTO institutionWithCertificatesDTO);
}
