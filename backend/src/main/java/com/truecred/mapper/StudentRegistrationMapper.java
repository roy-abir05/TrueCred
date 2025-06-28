package com.truecred.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.StudentRegistrationDTO;
import com.truecred.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentRegistrationMapper {
    StudentRegistrationDTO toDTO(Student student);

    @Mapping(target = "certificates", ignore = true)
    Student toEntity(StudentRegistrationDTO studentRegistrationDTO);
}
