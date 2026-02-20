package com.hotel.book.service.Impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.book.dto.UserRegisterRequest;
import com.hotel.book.entity.Role;
import com.hotel.book.entity.User;
import com.hotel.book.exception.BusinessException;
import com.hotel.book.repository.UserRepository;
import com.hotel.book.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already exists");
        }

        if (request.getRole() != Role.ADMIN && request.getRole() != Role.STAFF) {
            throw new BusinessException("Invalid role");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
    }
}