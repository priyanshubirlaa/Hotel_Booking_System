package com.hotel.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.book.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
