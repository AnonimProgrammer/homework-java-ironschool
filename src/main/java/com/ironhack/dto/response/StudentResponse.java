package com.ironhack.dto.response;

public record StudentResponse(
        String id,
        String name,
        String address,
        String email,
        CourseResponse course
) {}
