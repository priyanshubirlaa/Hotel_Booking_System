package com.hotel.book.service.Impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        room.setHotel(hotel);

        Room saved = roomRepository.save(room);
        return mapToResponse(saved);
    }

    @Override
    public Page<RoomResponseDTO> getRoomsByHotel(Long hotelId, Pageable pageable) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        List<Room> rooms = roomRepository.findByHotel(hotel);

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), rooms.size());

        List<RoomResponseDTO> content = rooms.subList(start, end)
                .stream()
                .map(this::mapToResponse)
                .toList();

        return new PageImpl<>(content, pageable, rooms.size());
    }

    @Override
    public RoomResponseDTO getRoomByHotelAndRoom(Long hotelId, Long roomId) {

        Room room = roomRepository
                .findByIdAndHotelId(roomId, hotelId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found for this hotel"));

        return mapToResponse(room);
    }

    private RoomResponseDTO mapToResponse(Room room) {
        return RoomResponseDTO.builder()
                .id(room.getId())
                .type(room.getType())
                .price(room.getPrice())
                .build();
    }
}
