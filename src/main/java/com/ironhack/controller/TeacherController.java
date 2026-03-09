package com.ironhack.controller;

import com.ironhack.dto.request.CreateTeacherRequest;
import com.ironhack.dto.request.UpdateTeacherRequest;
import com.ironhack.dto.response.TeacherResponse;
import com.ironhack.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(
            @Valid @RequestBody CreateTeacherRequest request
    ) {
        TeacherResponse createdTeacher = teacherService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getTeachers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary
    ) {
        List<TeacherResponse> teachers = teacherService.getTeachers(name, minSalary, maxSalary);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(
            @PathVariable String id
    ) {
        TeacherResponse teacher = teacherService.getById(id);
        return ResponseEntity.ok(teacher);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeacherResponse> updateTeacher(
            @PathVariable String id,
            @Valid @RequestBody UpdateTeacherRequest request
    ) {
        TeacherResponse updatedTeacher = teacherService.update(id, request);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(
            @PathVariable String id
    ) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
