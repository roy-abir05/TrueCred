package com.truecred.mapper.student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.truecred.dto.student.StudentRegistrationDTO;
import com.truecred.entity.Student;
import com.truecred.mapper.walletAddress.WalletAddressMapper;

@Mapper(componentModel = "spring", uses = { WalletAddressMapper.class })
public interface StudentRegistrationMapper {
    StudentRegistrationDTO toDTO(Student student);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "approved", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    Student toEntity(StudentRegistrationDTO studentRegistrationDTO);
}
