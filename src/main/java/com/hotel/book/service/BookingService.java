package com.hotel.book.service;

import java.util.List;

import com.hotel.book.dto.BookingRequestDTO;
import com.hotel.book.dto.BookingResponseDTO;
import com.hotel.book.entity.BookingStatus;

public interface BookingService {

    BookingResponseDTO createBooking(BookingRequestDTO request);

    BookingResponseDTO getBookingById(Long id);

    BookingResponseDTO cancelBooking(Long id);

    List<BookingResponseDTO> getBookingsByStatus(BookingStatus status);
}

