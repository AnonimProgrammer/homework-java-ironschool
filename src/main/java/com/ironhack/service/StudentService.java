package com.ironhack.service;

import com.ironhack.dto.request.CreateStudentRequest;
import com.ironhack.dto.request.UpdateStudentRequest;
import com.ironhack.dto.response.StudentResponse;
import com.ironhack.exception.NotFoundException;
import com.ironhack.mapper.StudentMapper;
import com.ironhack.model.Course;
import com.ironhack.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    private final CourseService courseService;
    private final StudentMapper studentMapper;

    public StudentService(CourseService courseService, StudentMapper studentMapper) {
        this.courseService = courseService;
        this.studentMapper = studentMapper;
    }

    public StudentResponse create(CreateStudentRequest request) {
        Student student = studentMapper.toModel(request);
        if (request.courseId() != null) {
            Course course = courseService.getModelById(request.courseId());
            student.setCourse(course);
        }

        students.put(student.getId(), student);
        return studentMapper.toResponse(student);
    }

    public StudentResponse getById(String id) {
        Student student = findOrThrow(id);
        return studentMapper.toResponse(student);
    }

    public List<StudentResponse> getStudents(
            String name, String address, String email, String courseId
    ) {
        return students.values().stream()
                .filter(student -> name == null || student.getName().contains(name))
                .filter(student -> address == null || student.getAddress().contains(address))
                .filter(student -> email == null || student.getEmail().contains(email))
                .filter(student -> courseId == null ||
                        (student.getCourse() != null && student.getCourse().getId().equals(courseId)))
                .map(studentMapper::toResponse)
                .toList();
    }

    public StudentResponse update(String id, UpdateStudentRequest request) {
        Student existingStudent = findOrThrow(id);

        if (request.name() != null) {
            existingStudent.setName(request.name());
        }

        if (request.address() != null) {
            existingStudent.setAddress(request.address());
        }

        if (request.email() != null) {
            existingStudent.setEmail(request.email());
        }

        if (request.courseId() != null) {
            Course course = courseService.getModelById(request.courseId());
            existingStudent.setCourse(course);
        }

        return studentMapper.toResponse(existingStudent);
    }

    public void delete(String id) {
        findOrThrow(id);
        students.remove(id);
    }

    private Student findOrThrow(String id) {
        Student student = students.get(id);
        if (student == null) {
            throw new NotFoundException("Student with id " + id + " not found");
        }
        return student;
    }
}
