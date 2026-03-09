package com.ironhack.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateStudentRequest(
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
        String address,

        @Email(message = "Email must be valid")
        String email,

        String courseId
) {}