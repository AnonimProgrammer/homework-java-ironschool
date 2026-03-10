package com.ironhack.service;

import com.ironhack.dto.request.CreateStudentRequest;
import com.ironhack.dto.request.UpdateStudentRequest;
import com.ironhack.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse create(CreateStudentRequest request);

    StudentResponse getById(String id);

    List<StudentResponse> getStudents(String name, String address, String email, String courseId);

    StudentResponse update(String id, UpdateStudentRequest request);

    void delete(String id);
}
