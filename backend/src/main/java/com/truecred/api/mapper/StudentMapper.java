package com.truecred.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.api.dto.StudentDTO;
import com.truecred.api.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);

    @Mapping(target = "certificates", ignore = true)
    @Mapping(target = "password", ignore = true)
    Student toEntity(StudentDTO studentDTO);
}
