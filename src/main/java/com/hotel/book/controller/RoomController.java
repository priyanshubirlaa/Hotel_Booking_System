package com.hotel.book.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.book.dto.RoomRequestDTO;
import com.hotel.book.dto.RoomResponseDTO;
import com.hotel.book.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponseDTO> addRoomToHotel(
            @PathVariable Long hotelId,
            @Validated @RequestBody RoomRequestDTO request) {
        RoomResponseDTO response = roomService.addRoomToHotel(hotelId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<RoomResponseDTO>> getAvailableRooms(
            @PathVariable Long hotelId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<RoomResponseDTO> result = roomService.getRoomsByHotel(hotelId, pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{roomId}")
public ResponseEntity<RoomResponseDTO> getRoom(
        @PathVariable Long hotelId,
        @PathVariable Long roomId) {

    return ResponseEntity.ok(
            roomService.getRoomByHotelAndRoom(hotelId, roomId));
}

}

