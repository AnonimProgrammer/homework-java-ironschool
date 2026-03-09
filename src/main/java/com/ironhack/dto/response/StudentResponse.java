package com.ironhack.dto.response;

import com.ironhack.model.Course;

public record StudentResponse(
        String id,
        String name,
        String address,
        String email,
        Course course
) {}
