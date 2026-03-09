package com.ironhack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateCourseRequest(
        @NotBlank(message = "Course name is required")
        @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
        String name,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be a positive number")
        Double price,

        @NotNull(message = "Money earned is required")
        @Positive(message = "Money earned must be a positive number")
        Double moneyEarned,

        String teacherId
) {}
