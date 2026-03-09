package com.ironhack.service;

import com.ironhack.dto.CreateTeacherRequest;
import com.ironhack.dto.TeacherResponse;
import com.ironhack.dto.UpdateTeacherRequest;
import com.ironhack.exception.NotFoundException;
import com.ironhack.mapper.TeacherMapper;
import com.ironhack.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {
    private final Map<String, Teacher> teachers = new HashMap<>();

    private final TeacherMapper mapper;

    public TeacherService(TeacherMapper mapper) {
        this.mapper = mapper;
    }

    public TeacherResponse create(CreateTeacherRequest request) {
        Teacher teacher = mapper.toModel(request);
        teachers.put(teacher.getId(), teacher);
        return mapper.toResponse(teacher);
    }

    public TeacherResponse getById(String id) {
        return mapper.toResponse(findOrThrow(id));
    }

    // Direct access to Teacher model for CourseService
    public Teacher getModelById(String id) {
        return findOrThrow(id);
    }

    public List<TeacherResponse> getTeachers(String name, Double minSalary, Double maxSalary) {
        return teachers.values().stream()
                .filter(t -> name == null || t.getName().equalsIgnoreCase(name))
                .filter(t -> minSalary == null || t.getSalary() >= minSalary)
                .filter(t -> maxSalary == null || t.getSalary() <= maxSalary)
                .map(mapper::toResponse)
                .toList();
    }

    public TeacherResponse update(String id, UpdateTeacherRequest request) {
        Teacher existing = findOrThrow(id);
        if (request.name() != null) existing.setName(request.name());
        if (request.salary() != null) existing.setSalary(request.salary());
        return mapper.toResponse(existing);
    }

    public void delete(String id) {
        findOrThrow(id);
        teachers.remove(id);
    }

    private Teacher findOrThrow(String id) {
        Teacher teacher = teachers.get(id);
        if (teacher == null) {
            throw new NotFoundException("Teacher with id " + id + " not found");
        }
        return teacher;
    }
}