package com.showb.firstboot.business.common.adapters.in.dtos;

import com.showb.firstboot.exceptions.FirstbootException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public record ErrorResponse(
        String type,
        String message,
        LocalDateTime timestamp,
        String path
) {
    public ErrorResponse(FirstbootException ex, String path) {
        this(
                Objects.nonNull(ex.getExceptionType()) ? ex.getExceptionType().getType() : HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage(),
                LocalDateTime.now(),
                path
        );
    }
}
