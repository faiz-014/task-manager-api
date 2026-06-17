package com.Faiz.tasks.exceptions;

public record ErrorResponse(
        int status,
        String message
) {
}
