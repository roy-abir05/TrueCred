package com.truecred.mapper.Student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.Student.StudentRegistrationDTO;
import com.truecred.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentRegistrationMapper {
    StudentRegistrationDTO toDTO(Student student);

    @Mapping(target = "certificates", ignore = true)
    Student toEntity(StudentRegistrationDTO studentRegistrationDTO);
}
