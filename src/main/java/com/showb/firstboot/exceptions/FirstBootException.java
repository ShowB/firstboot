package com.showb.firstboot.exceptions;

public class FirstBootException extends RuntimeException {
    public FirstBootException(String message) {
        super(message);
    }

    public FirstBootException(String message, Throwable cause) {
        super(message, cause);
    }

    public FirstBootException(Throwable cause) {
        super(cause);
    }

    public FirstBootException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
