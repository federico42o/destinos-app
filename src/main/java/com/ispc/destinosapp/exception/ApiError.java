package com.ispc.destinosapp.exception;

public record ApiError(
        String message,
        String timestamp,
        Integer status,
        String path
) {
}
