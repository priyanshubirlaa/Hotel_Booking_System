package com.hotel.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.book.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
