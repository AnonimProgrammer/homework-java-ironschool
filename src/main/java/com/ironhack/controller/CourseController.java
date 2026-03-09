package com.ironhack.controller;

import com.ironhack.dto.CreateCourseRequest;
import com.ironhack.dto.CourseResponse;
import com.ironhack.dto.UpdateCourseRequest;
import com.ironhack.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(
            @Valid @RequestBody CreateCourseRequest request
    ) {
        CourseResponse createdCourse = courseService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getCourses(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Double moneyEarned,
            @RequestParam(required = false) String teacherId
    ) {
        List<CourseResponse> courses = courseService.getCourses(name, price, moneyEarned, teacherId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable String id
    ) {
        CourseResponse course = courseService.getById(id);
        return ResponseEntity.ok(course);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable String id,
            @Valid @RequestBody UpdateCourseRequest request
    ) {
        CourseResponse updatedCourse = courseService.update(id, request);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable String id
    ) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
