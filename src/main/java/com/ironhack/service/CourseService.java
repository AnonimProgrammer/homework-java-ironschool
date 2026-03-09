package com.ironhack.service;

import com.ironhack.dto.CreateCourseRequest;
import com.ironhack.dto.CourseResponse;
import com.ironhack.dto.UpdateCourseRequest;
import com.ironhack.exception.NotFoundException;
import com.ironhack.mapper.CourseMapper;
import com.ironhack.model.Course;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {
    private final Map<String, Course> courses = new HashMap<>();

    private final CourseMapper mapper;

    public CourseService(CourseMapper mapper) {
        this.mapper = mapper;
    }

    public CourseResponse create(CreateCourseRequest request) {
        if (request.teacherId() != null) {
            // Fetch teacher using teacher service
            // Check if teacher exists, if not throw NotFoundException
        }
        Course course = mapper.toModel(request);
        // Set the teacher

        courses.put(course.getId(), course);
        return mapper.toResponse(course);
    }

    public CourseResponse getById(String id) {
        return mapper.toResponse(findOrThrow(id));
    }

    public List<CourseResponse> getCourses(
            String name, Double price, Double moneyEarned, String teacherId
    ) {
        return courses.values().stream()
                .filter(course -> name == null || course.getName().contains(name))
                .filter(course -> price == null || course.getPrice().equals(price))
                .filter(course -> moneyEarned == null || course.getMoneyEarned().equals(moneyEarned))
                .filter(course -> teacherId == null ||
                        (course.getTeacher() != null && course.getTeacher().getId().equals(teacherId)))
                .map(mapper::toResponse)
                .toList();
    }

    public CourseResponse update(String id, UpdateCourseRequest request) {
        Course existingCourse = findOrThrow(id);

        if (request.name() != null) {
            existingCourse.setName(request.name());
        }

        if (request.price() != null) {
            existingCourse.setPrice(request.price());
        }

        if (request.moneyEarned() != null) {
            existingCourse.setMoneyEarned(request.moneyEarned());
        }

        if (request.teacherId() != null) {
            // Fetch teacher using teacher service
            // Check if teacher exists, if not throw NotFoundException
            // Set the teacher
        }

        return mapper.toResponse(existingCourse);
    }

    public void delete(String id) {
        findOrThrow(id);
        courses.remove(id);
    }

    private Course findOrThrow(String id) {
        Course course = courses.get(id);
        if (course == null) {
            throw new NotFoundException("Course with id " + id + " not found");
        }
        return course;
    }
}
