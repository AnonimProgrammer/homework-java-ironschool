package com.ironhack.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateTeacherRequest(
        @NotBlank(message = "Teacher name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotNull(message = "Salary is required")
        @Positive(message = "Salary must be a positive number")
        Double salary
) {}