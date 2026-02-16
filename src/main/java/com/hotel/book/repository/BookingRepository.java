package com.hotel.book.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.book.entity.Booking;
import com.hotel.book.entity.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Page<Booking> findByStatus(BookingStatus status, Pageable pageable);
}
