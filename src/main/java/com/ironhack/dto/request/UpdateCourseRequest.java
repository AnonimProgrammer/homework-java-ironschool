package com.ironhack.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateCourseRequest(
        @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
        String name,

        @Positive(message = "Price must be a positive number")
        Double price,

        @Positive(message = "Money earned must be a positive number")
        Double moneyEarned,

        String teacherId
) {}