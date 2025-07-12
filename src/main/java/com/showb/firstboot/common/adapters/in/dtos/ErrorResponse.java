package com.showb.firstboot.common.adapters.in.dtos;

import com.showb.firstboot.exceptions.FirstBootException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        String type,
        String message,
        LocalDateTime timestamp,
        String path
) {
    public ErrorResponse(FirstBootException ex, String path) {
        this(
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage(),
                LocalDateTime.now(),
                path
        );
    }
}
