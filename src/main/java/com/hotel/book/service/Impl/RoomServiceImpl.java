package com.hotel.book.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotel.book.dto.RoomRequestDTO;
import com.hotel.book.dto.RoomResponseDTO;
import com.hotel.book.entity.Hotel;
import com.hotel.book.entity.Room;
import com.hotel.book.exception.ResourceNotFoundException;
import com.hotel.book.repository.HotelRepository;
import com.hotel.book.repository.RoomRepository;
import com.hotel.book.service.RoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Override
    public RoomResponseDTO addRoomToHotel(Long hotelId, RoomRequestDTO request) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        Room room = new Room();
        room.setType(request.getType());
        room.setPrice(request.getPrice());
        room.setAvailable(true);
        room.setHotel(hotel);

        Room saved = roomRepository.save(room);
        return mapToResponse(saved);
    }

    @Override
    public List<RoomResponseDTO> getAvailableRoomsByHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        return hotel.getRooms()
                .stream()
                .filter(Room::getAvailable)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoomResponseDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        return mapToResponse(room);
    }

    private RoomResponseDTO mapToResponse(Room room) {
        return RoomResponseDTO.builder()
                .id(room.getId())
                .type(room.getType())
                .price(room.getPrice())
                .available(room.getAvailable())
                .build();
    }
}

