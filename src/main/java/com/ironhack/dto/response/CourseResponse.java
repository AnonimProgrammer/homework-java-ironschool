package com.ironhack.dto.response;

public record CourseResponse (
        String id,
        String name,
        Double price,
        Double moneyEarned,
        TeacherResponse teacher
) {}
