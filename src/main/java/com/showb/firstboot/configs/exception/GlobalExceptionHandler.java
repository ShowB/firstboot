package com.showb.firstboot.configs.exception;

import com.showb.firstboot.business.common.adapters.in.dtos.ErrorResponse;
import com.showb.firstboot.exceptions.FirstBootException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FirstBootException.class)
    public ResponseEntity<ErrorResponse> handleInboundServiceException(FirstBootException ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        this.log(ex, path);
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(ex, path));
    }

    private void log(FirstBootException ex, String path) {
        log.error("## Exception Occurred at '{}'", path);
        log.error("## Message: {}", ex.getMessage(), ex);
    }
}
