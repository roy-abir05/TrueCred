package com.truecred.mapper.Student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.truecred.dto.Student.StudentDTO;
import com.truecred.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);

    @Mapping(target = "certificates", ignore = true)
    @Mapping(target = "password", ignore = true)
    Student toEntity(StudentDTO studentDTO);
}
