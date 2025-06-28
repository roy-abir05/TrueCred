package com.truecred.mapper.Institution;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.Institution.InstitutionDTO;
import com.truecred.entity.Institution;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {
    
    InstitutionDTO toDTO(Institution institution);

    @Mapping(target = "issuedCertificates", ignore = true)
    @Mapping(target = "password", ignore = true)
    Institution toEntity(InstitutionDTO institutionDTO);
}
