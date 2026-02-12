package com.hotel.book.service;

import com.hotel.book.dto.BookingRequestDTO;
import com.hotel.book.dto.BookingResponseDTO;

public interface BookingService {

    BookingResponseDTO createBooking(BookingRequestDTO request);

    BookingResponseDTO getBookingById(Long id);

    BookingResponseDTO cancelBooking(Long id);
}

