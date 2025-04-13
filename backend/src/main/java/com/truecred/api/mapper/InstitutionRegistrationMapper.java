package com.truecred.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.api.dto.InstitutionRegistrationDTO;
import com.truecred.api.entity.Institution;

@Mapper(componentModel = "spring")
public interface InstitutionRegistrationMapper {
    
    InstitutionRegistrationDTO toDTO(Institution institution);

    @Mapping(target = "issuedCertificates", ignore = true)
    Institution toEntity(InstitutionRegistrationDTO institutionRegistrationDTO);
}
