package com.hotel.book.dto;

import jakarta.validation.constraints.NotNull;

public class BookingRequestDTO {

    @NotNull
    private Long customerId;

    
    @NotNull
    private Long hotelId;

    @NotNull
    private Long roomId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
