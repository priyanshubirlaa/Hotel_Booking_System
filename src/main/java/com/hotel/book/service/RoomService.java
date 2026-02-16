package com.hotel.book.service;

import com.hotel.book.dto.RoomRequestDTO;
import com.hotel.book.dto.RoomResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    RoomResponseDTO addRoomToHotel(Long hotelId, RoomRequestDTO request);

    Page<RoomResponseDTO> getAvailableRoomsByHotel(Long hotelId, Pageable pageable);

    RoomResponseDTO getRoomById(Long id);
}

