package com.hotel.book.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<RoomResponseDTO>> getAvailableRooms(@PathVariable Long hotelId) {
        return ResponseEntity.ok(roomService.getAvailableRoomsByHotel(hotelId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponseDTO> getRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }
}

