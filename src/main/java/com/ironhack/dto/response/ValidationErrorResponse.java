package com.ironhack.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponse(
        Integer status,
        String error,
        Map<String, String> errors,
        LocalDateTime timestamp
) {}