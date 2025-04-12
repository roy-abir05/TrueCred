package com.truecred.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.api.dto.StudentRegistrationDTO;
import com.truecred.api.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentRegistrationMapper {
    StudentRegistrationDTO toDTO(Student student);

    @Mapping(target = "certificates", ignore = true)
    Student toEntity(StudentRegistrationDTO studentRegistrationDTO);
}
