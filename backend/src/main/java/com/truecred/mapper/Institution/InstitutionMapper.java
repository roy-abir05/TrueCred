package com.truecred.mapper.Institution;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.institution.InstitutionDTO;
import com.truecred.entity.Institution;
import com.truecred.mapper.walletAddress.WalletAddressMapper;

@Mapper(componentModel = "spring", uses = { WalletAddressMapper.class })
public interface InstitutionMapper {

    InstitutionDTO toDTO(Institution institution);

    @Mapping(target = "password", ignore = true)
    Institution toEntity(InstitutionDTO institutionDTO);
}
