package com.truecred.mapper.Institution;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.truecred.dto.institution.InstitutionRegistrationDTO;
import com.truecred.entity.Institution;
import com.truecred.mapper.walletAddress.WalletAddressMapper;

@Mapper(componentModel = "spring", uses = { WalletAddressMapper.class })
public interface InstitutionRegistrationMapper {

    InstitutionRegistrationDTO toDTO(Institution institution);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "approved", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true)
    })
    Institution toEntity(InstitutionRegistrationDTO institutionRegistrationDTO);
}
