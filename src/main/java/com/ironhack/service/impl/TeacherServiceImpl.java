package com.ironhack.service.impl;

import com.ironhack.dto.request.CreateTeacherRequest;
import com.ironhack.dto.response.TeacherResponse;
import com.ironhack.dto.request.UpdateTeacherRequest;
import com.ironhack.exception.NotFoundException;
import com.ironhack.mapper.TeacherMapper;
import com.ironhack.model.Teacher;
import com.ironhack.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final Map<String, Teacher> teachers = new HashMap<>();

    private final TeacherMapper mapper;

    public TeacherServiceImpl(TeacherMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public TeacherResponse create(CreateTeacherRequest request) {
        Teacher teacher = mapper.toModel(request);

        teachers.put(teacher.getId(), teacher);
        return mapper.toResponse(teacher);
    }

    @Override
    public TeacherResponse getById(String id) {
        return mapper.toResponse(findOrThrow(id));
    }

    @Override
    public Teacher getModelById(String id) {
        return findOrThrow(id);
    }

    @Override
    public List<TeacherResponse> getTeachers(
            String name, Double minSalary, Double maxSalary
    ) {
        return teachers.values().stream()
                .filter(t -> name == null || t.getName().equalsIgnoreCase(name))
                .filter(t -> minSalary == null || t.getSalary() >= minSalary)
                .filter(t -> maxSalary == null || t.getSalary() <= maxSalary)
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public TeacherResponse update(String id, UpdateTeacherRequest request) {
        Teacher existing = findOrThrow(id);

        if (request.name() != null) {
            existing.setName(request.name());
        }

        if (request.salary() != null) {
            existing.setSalary(request.salary());
        }

        return mapper.toResponse(existing);
    }

    @Override
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