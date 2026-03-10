package com.ironhack.mapper;

import com.ironhack.dto.request.CreateCourseRequest;
import com.ironhack.dto.response.CourseResponse;
import com.ironhack.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface CourseMapper {
    @Mapping(target = "teacher", ignore = true)
    Course toModel(CreateCourseRequest request);

    CourseResponse toResponse(Course course);
}
