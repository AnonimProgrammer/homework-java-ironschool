package com.ironhack.controller;

import com.ironhack.dto.request.CreateStudentRequest;
import com.ironhack.dto.request.UpdateStudentRequest;
import com.ironhack.dto.response.StudentResponse;
import com.ironhack.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(
            @Valid @RequestBody CreateStudentRequest request
    ) {
        StudentResponse createdStudent = studentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String courseId
    ) {
        List<StudentResponse> students = studentService.getStudents(name, address, email, courseId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable String id
    ) {
        StudentResponse student = studentService.getById(id);
        return ResponseEntity.ok(student);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable String id,
            @Valid @RequestBody UpdateStudentRequest request
    ) {
        StudentResponse updatedStudent = studentService.update(id, request);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable String id
    ) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
