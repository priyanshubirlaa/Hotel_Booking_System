package com.hotel.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.book.entity.Booking;
import com.hotel.book.entity.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findByStatus(BookingStatus status);
}
