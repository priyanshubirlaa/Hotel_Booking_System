package com.hotel.book.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public Page<HotelResponseDTO> getHotels(String city, Pageable pageable) {

    Page<Hotel> page;

    if (city != null && !city.isBlank()) {
        page = hotelRepository.findByLocationContainingIgnoreCase(city, pageable);
    } else {
        page = hotelRepository.findAll(pageable);
    }

    return page.map(this::mapToResponse);
}

    private HotelResponseDTO mapToResponse(Hotel hotel) {
        return HotelResponseDTO.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .build();
    }
}

