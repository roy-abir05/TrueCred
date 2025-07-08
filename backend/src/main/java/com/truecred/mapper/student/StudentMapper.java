package com.truecred.mapper.student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.student.StudentDTO;
import com.truecred.entity.Student;
import com.truecred.mapper.walletAddress.WalletAddressMapper;

@Mapper(componentModel = "spring", uses = { WalletAddressMapper.class })
public interface StudentMapper {
    StudentDTO toDTO(Student student);

    @Mapping(target = "password", ignore = true)
    Student toEntity(StudentDTO studentDTO);
}
