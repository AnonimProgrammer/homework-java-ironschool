package com.ironhack.dto;

import jakarta.validation.constraints.*;

public record UpdateTeacherRequest(
        @Size(min = 2, max = 100)
        String name,

        @Positive
        Double salary
) {}