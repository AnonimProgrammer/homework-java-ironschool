package com.ironhack.service;

import com.ironhack.dto.request.CreateCourseRequest;
import com.ironhack.dto.request.UpdateCourseRequest;
import com.ironhack.dto.response.CourseResponse;
import com.ironhack.model.Course;

import java.util.List;

public interface CourseService {
    CourseResponse create(CreateCourseRequest request);

    CourseResponse getById(String id);

    Course getModelById(String id);

    List<CourseResponse> getCourses(String name, Double price, Double moneyEarned, String teacherId);

    CourseResponse update(String id, UpdateCourseRequest request);

    void delete(String id);
}
