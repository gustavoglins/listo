package com.listo.exceptions.response;

import java.time.LocalDateTime;

public record ExceptionResponse(String message, String details, LocalDateTime timestamp) {
}
