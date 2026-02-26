package com.hotel.book.dto;

import java.time.LocalDate;

import com.hotel.book.entity.BookingStatus;

public class BookingResponseDTO {

    private Long bookingId;
    private String customerName;
    private String hotelName;
    private String roomType;
    private BookingStatus status;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public BookingResponseDTO() {
    }

    public BookingResponseDTO(Long bookingId,
                              String customerName,
                              String hotelName,
                              String roomType,
                              BookingStatus status,
                              LocalDate checkInDate,
                              LocalDate checkOutDate) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.status = status;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public static BookingResponseDTOBuilder builder() {
        return new BookingResponseDTOBuilder();
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public static class BookingResponseDTOBuilder {

        private Long bookingId;
        private String customerName;
        private String hotelName;
        private String roomType;
        private BookingStatus status;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;

        public BookingResponseDTOBuilder bookingId(Long bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public BookingResponseDTOBuilder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public BookingResponseDTOBuilder hotelName(String hotelName) {
            this.hotelName = hotelName;
            return this;
        }

        public BookingResponseDTOBuilder roomType(String roomType) {
            this.roomType = roomType;
            return this;
        }

        public BookingResponseDTOBuilder status(BookingStatus status) {
            this.status = status;
            return this;
        }

        public BookingResponseDTOBuilder checkInDate(LocalDate checkInDate) {
            this.checkInDate = checkInDate;
            return this;
        }

        public BookingResponseDTOBuilder checkOutDate(LocalDate checkOutDate) {
            this.checkOutDate = checkOutDate;
            return this;
        }

        public BookingResponseDTO build() {
            return new BookingResponseDTO(
                    bookingId,
                    customerName,
                    hotelName,
                    roomType,
                    status,
                    checkInDate,
                    checkOutDate
            );
        }
    }
}