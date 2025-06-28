package com.truecred.mapper.Institution;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.Institution.InstitutionRegistrationDTO;
import com.truecred.entity.Institution;

@Mapper(componentModel = "spring")
public interface InstitutionRegistrationMapper {
    
    InstitutionRegistrationDTO toDTO(Institution institution);

    @Mapping(target = "issuedCertificates", ignore = true)
    Institution toEntity(InstitutionRegistrationDTO institutionRegistrationDTO);
}
