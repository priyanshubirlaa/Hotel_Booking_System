package com.hotel.book.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;

import com.hotel.book.repository.CustomerRepository;
import com.hotel.book.repository.UserRepository;
import com.hotel.book.entity.Customer;
import com.hotel.book.entity.Role;
import com.hotel.book.entity.User;
import com.hotel.book.security.JwtUtil;
import com.hotel.book.service.UserService;
import com.hotel.book.dto.LoginRequest;
import com.hotel.book.dto.UserRegisterRequest;
import com.hotel.book.dto.AuthResponse;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserService userService;

//     @PostMapping("/register")
//     public AuthResponse register(@RequestBody RegisterRequest request) {

//         Customer customer = Customer.builder()
//                 .name(request.getName())
//                 .email(request.getEmail())
//                 .phone(request.getPhone())
//                 .password(passwordEncoder.encode(request.getPassword()))
//                 .role(Role.CUSTOMER)
//                 .build();

//         repository.save(customer);

//         String token = jwtUtil.generateToken(customer.getEmail(),
//                 customer.getRole().name());

//         return new AuthResponse(token, customer.getRole().name());
//     }

@PostMapping("/register")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<String> register(@Validated @RequestBody UserRegisterRequest request) {

    userService.registerUser(request);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body("User created successfully");
}

    @PostMapping("/login")
public AuthResponse login(@RequestBody LoginRequest request) {

    authManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));

    String token = jwtUtil.generateToken(
            user.getEmail(),
            user.getRole().name());

    return new AuthResponse(token, user.getRole().name());
}
}
