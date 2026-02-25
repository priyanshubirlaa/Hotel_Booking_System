package com.hotel.book.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.book.dto.BookingRequestDTO;
import com.hotel.book.dto.BookingResponseDTO;
import com.hotel.book.entity.*;
import com.hotel.book.exception.BusinessException;
import com.hotel.book.exception.ResourceNotFoundException;
import com.hotel.book.repository.*;
import com.hotel.book.service.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO request) {

        if (!request.getCheckOutDate().isAfter(request.getCheckInDate())) {
            throw new BusinessException("Check-out date must be after check-in date");
        }

        if (request.getCheckInDate().isBefore(LocalDate.now())) {
            throw new BusinessException("Check-in date cannot be in the past");
        }

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        Room room = roomRepository.findByIdAndHotelId(request.getRoomId(), request.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        List<Booking> overlapping = bookingRepository.findOverlappingBookings(
                room.getId(),
                request.getCheckInDate(),
                request.getCheckOutDate()
        );

        if (!overlapping.isEmpty()) {
            throw new BusinessException("Room already booked for selected dates");
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setHotel(hotel);
        booking.setRoom(room);
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setStatus(BookingStatus.CONFIRMED);

        Booking saved = bookingRepository.save(booking);
        return mapToResponse(saved);
    }

    @Override
    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return mapToResponse(booking);
    }

    @Override
    @Transactional
    public BookingResponseDTO cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
                
                try {
                    Thread.sleep(10000); // 10 seconds delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        booking.setStatus(BookingStatus.CANCELLED);
        return mapToResponse(bookingRepository.save(booking));
    }

    @Override
    public Page<BookingResponseDTO> getBookingsByStatus(BookingStatus status, Pageable pageable) {
        return bookingRepository.findByStatus(status, pageable)
                .map(this::mapToResponse);
    }

    private BookingResponseDTO mapToResponse(Booking booking) {
        return BookingResponseDTO.builder()
                .bookingId(booking.getId())
                .customerName(booking.getCustomer().getName())
                .hotelName(booking.getHotel().getName())
                .roomType(booking.getRoom().getType())
                .status(booking.getStatus())
                .build();
    }
}
