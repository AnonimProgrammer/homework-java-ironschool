package com.ironhack.dto;

import com.ironhack.model.Teacher;

public record CourseResponse (
        String id,
        String name,
        Double price,
        Double moneyEarned,
        Teacher teacher
) {}
