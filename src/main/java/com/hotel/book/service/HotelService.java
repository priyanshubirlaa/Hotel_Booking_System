package com.hotel.book.service;

import java.util.List;

import com.hotel.book.dto.HotelRequestDTO;
import com.hotel.book.dto.HotelResponseDTO;

public interface HotelService {

    HotelResponseDTO addHotel(HotelRequestDTO request);

    HotelResponseDTO getHotelById(Long id);

    List<HotelResponseDTO> getAllHotels();
}

