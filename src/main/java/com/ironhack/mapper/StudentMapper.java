package com.ironhack.mapper;

import com.ironhack.dto.request.CreateStudentRequest;
import com.ironhack.dto.response.StudentResponse;
import com.ironhack.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "course", ignore = true)
    Student toModel(CreateStudentRequest request);

    StudentResponse toResponse(Student student);
}
