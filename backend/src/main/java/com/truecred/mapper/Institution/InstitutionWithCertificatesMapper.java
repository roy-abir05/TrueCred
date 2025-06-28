package com.truecred.mapper.Institution;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.Institution.InstitutionWithCertificatesDTO;
import com.truecred.entity.Institution;
import com.truecred.mapper.Certificate.CertificateMapper;

@Mapper(componentModel = "spring", uses = CertificateMapper.class)
public interface InstitutionWithCertificatesMapper {

    InstitutionWithCertificatesDTO toDTO(Institution institution);

    @Mapping(target = "password", ignore = true)
    Institution toEntity(InstitutionWithCertificatesDTO institutionWithCertificatesDTO);
}
