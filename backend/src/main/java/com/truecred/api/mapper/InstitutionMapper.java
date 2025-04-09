package com.truecred.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.api.dto.InstitutionDTO;
import com.truecred.api.entity.Institution;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {
    
    InstitutionDTO toDTO(Institution institution);

    @Mapping(target = "issuedCertificates", ignore = true)
    @Mapping(target = "password", ignore = true)
    Institution toEntity(InstitutionDTO institutionDTO);
}
