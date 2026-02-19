package com.hotel.book.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hotel.book.repository.CustomerRepository;
import com.hotel.book.entity.Customer;
import com.hotel.book.entity.Role;
import com.hotel.book.security.JwtUtil;
import com.hotel.book.dto.RegisterRequest;
import com.hotel.book.dto.LoginRequest;
import com.hotel.book.dto.AuthResponse;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .build();

        repository.save(customer);

        String token = jwtUtil.generateToken(customer.getEmail(),
                customer.getRole().name());

        return new AuthResponse(token, customer.getRole().name());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Customer customer = repository.findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtUtil.generateToken(customer.getEmail(),
                customer.getRole().name());

        return new AuthResponse(token, customer.getRole().name());
    }
}
