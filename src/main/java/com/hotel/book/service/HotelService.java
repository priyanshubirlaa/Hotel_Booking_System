package com.hotel.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hotel.book.dto.HotelRequestDTO;
import com.hotel.book.dto.HotelResponseDTO;

public interface HotelService {

    HotelResponseDTO addHotel(HotelRequestDTO request);

    HotelResponseDTO getHotelById(Long id);

    Page<HotelResponseDTO> getHotels(String locationFilter, Pageable pageable);
}

