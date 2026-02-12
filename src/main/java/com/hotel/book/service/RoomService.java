package com.hotel.book.service;

import java.util.List;

import com.hotel.book.dto.RoomRequestDTO;
import com.hotel.book.dto.RoomResponseDTO;

public interface RoomService {

    RoomResponseDTO addRoomToHotel(Long hotelId, RoomRequestDTO request);

    List<RoomResponseDTO> getAvailableRoomsByHotel(Long hotelId);

    RoomResponseDTO getRoomById(Long id);
}

