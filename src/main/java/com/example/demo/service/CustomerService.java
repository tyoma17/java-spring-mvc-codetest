package com.example.demo.service;

import com.example.demo.dto.CustomerDto;

public interface CustomerService {

    CustomerDto[] sort(CustomerDto[] customers);
}