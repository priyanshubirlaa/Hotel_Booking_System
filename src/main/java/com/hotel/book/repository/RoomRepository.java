package com.hotel.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.book.entity.Hotel;
import com.hotel.book.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotel(Hotel hotel);

    Optional<Room> findByIdAndHotelId(Long roomId, Long hotelId);
}
