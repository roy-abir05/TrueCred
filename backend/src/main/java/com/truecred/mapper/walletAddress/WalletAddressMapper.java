package com.truecred.mapper.walletAddress;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.walletAddress.WalletAddressDTO;
import com.truecred.entity.WalletAddress;

@Mapper(componentModel = "spring")
public interface WalletAddressMapper {
    WalletAddressDTO toDTO(WalletAddress walletAddress);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "institution", ignore = true)
    @Mapping(target = "student", ignore = true)
    WalletAddress toEntity(WalletAddressDTO walletAddressDTO);
}