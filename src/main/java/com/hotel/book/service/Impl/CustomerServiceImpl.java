package com.hotel.book.service.Impl;

import org.springframework.stereotype.Service;

import com.hotel.book.dto.CustomerRequestDTO;
import com.hotel.book.dto.CustomerResponseDTO;
import com.hotel.book.entity.Customer;
import com.hotel.book.repository.CustomerRepository;
import com.hotel.book.service.CustomerService;
import com.hotel.book.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO request) {

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        Customer saved = repository.save(customer);

        return mapToResponse(saved);
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {

        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return mapToResponse(customer);
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO request) {

        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        return mapToResponse(repository.save(customer));
    }

    private CustomerResponseDTO mapToResponse(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }
}
