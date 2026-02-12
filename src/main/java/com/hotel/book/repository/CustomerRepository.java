package com.hotel.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.book.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
