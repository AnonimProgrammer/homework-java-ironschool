package com.ironhack.mapper;

import com.ironhack.dto.request.CreateTeacherRequest;
import com.ironhack.dto.response.TeacherResponse;
import com.ironhack.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher toModel(CreateTeacherRequest request);

    TeacherResponse toResponse(Teacher teacher);
}