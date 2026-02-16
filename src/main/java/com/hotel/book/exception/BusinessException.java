package com.hotel.book.exception;

/**
 * Represents domain/business rule violations (e.g. room not available,
 * invalid status values, etc.).
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}

