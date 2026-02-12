package com.hotel.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.book.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
