package com.hotel.book.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotel.book.dto.BookingRequestDTO;
import com.hotel.book.dto.BookingResponseDTO;
import com.hotel.book.entity.Booking;
import com.hotel.book.entity.BookingStatus;
import com.hotel.book.entity.Customer;
import com.hotel.book.entity.Hotel;
import com.hotel.book.entity.Room;
import com.hotel.book.exception.BusinessException;
import com.hotel.book.exception.ResourceNotFoundException;
import com.hotel.book.repository.BookingRepository;
import com.hotel.book.repository.CustomerRepository;
import com.hotel.book.repository.HotelRepository;
import com.hotel.book.repository.RoomRepository;
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
    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        if (!Boolean.TRUE.equals(room.getAvailable())) {
            throw new BusinessException("Room is not available");
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setHotel(hotel);
        booking.setRoom(room);
        booking.setStatus(BookingStatus.CONFIRMED);

        // mark room as unavailable
        room.setAvailable(false);
        roomRepository.save(room);

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
    public BookingResponseDTO cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);

        Room room = booking.getRoom();
        room.setAvailable(true);
        roomRepository.save(room);

        Booking saved = bookingRepository.save(booking);
        return mapToResponse(saved);
    }

    @Override
    public List<BookingResponseDTO> getBookingsByStatus(BookingStatus status) {
        List<Booking> bookings = bookingRepository.findByStatus(status);
        return bookings.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
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

