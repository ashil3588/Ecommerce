package com.example.library.service;

import com.example.library.dto.CustomerDto;
import com.example.library.model.Customer;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;


public interface CustomerService {
    Customer findByEmail(String email);


    Customer save(CustomerDto customerDto);

    List<Customer> findAll();

    Customer findById(long id);

    void disable(long id);

    void enable(long id);


}
