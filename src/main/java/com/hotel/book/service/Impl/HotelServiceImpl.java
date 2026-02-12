package com.hotel.book.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotel.book.dto.HotelRequestDTO;
import com.hotel.book.dto.HotelResponseDTO;
import com.hotel.book.entity.Hotel;
import com.hotel.book.exception.ResourceNotFoundException;
import com.hotel.book.repository.HotelRepository;
import com.hotel.book.service.HotelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelResponseDTO addHotel(HotelRequestDTO request) {
        Hotel hotel = new Hotel();
        hotel.setName(request.getName());
        hotel.setLocation(request.getLocation());

        Hotel saved = hotelRepository.save(hotel);
        return mapToResponse(saved);
    }

    @Override
    public HotelResponseDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        return mapToResponse(hotel);
    }

    @Override
    public List<HotelResponseDTO> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private HotelResponseDTO mapToResponse(Hotel hotel) {
        return HotelResponseDTO.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .build();
    }
}

