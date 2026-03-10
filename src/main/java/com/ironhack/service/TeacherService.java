package com.ironhack.service;

import com.ironhack.dto.request.CreateTeacherRequest;
import com.ironhack.dto.request.UpdateTeacherRequest;
import com.ironhack.dto.response.TeacherResponse;
import com.ironhack.model.Teacher;

import java.util.List;

public interface TeacherService {
    TeacherResponse create(CreateTeacherRequest request);

    TeacherResponse getById(String id);

    Teacher getModelById(String id);

    List<TeacherResponse> getTeachers(String name, Double minSalary, Double maxSalary);

    TeacherResponse update(String id, UpdateTeacherRequest request);

    void delete(String id);
}
