package com.hotel.book.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.book.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Page<Room> findByHotelId(Long hotelId, Pageable pageable);
    

    Optional<Room> findByIdAndHotelId(Long roomId, Long hotelId);
}
