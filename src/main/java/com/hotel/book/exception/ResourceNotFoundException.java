package com.hotel.book.exception;

/**
 * Thrown when a requested resource (Customer, Hotel, Room, Booking, etc.)
 * cannot be found in the database.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

