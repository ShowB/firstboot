package com.showb.firstboot.exceptions;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Objects;

public class FirstbootException extends RuntimeException {
    @Getter
    private final transient ExceptionType exceptionType;


    public FirstbootException(@NotNull ExceptionType exceptionType, @NotNull Object... messageParams) {
        super(generateParameterizedMessage(exceptionType.getMessage(), messageParams));
        this.exceptionType = exceptionType;
    }


    private static String generateParameterizedMessage(String template, Object[] params) {
        if (Objects.isNull(params)) {
            return template;
        }

        for (Object param : params) {
            template = template.replaceFirst("\\{}", param.toString());
        }

        return template;
    }
}
