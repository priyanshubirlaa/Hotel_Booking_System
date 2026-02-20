package com.hotel.book.service;

import com.hotel.book.dto.UserRegisterRequest;

public interface UserService {
    void registerUser(UserRegisterRequest request);
}