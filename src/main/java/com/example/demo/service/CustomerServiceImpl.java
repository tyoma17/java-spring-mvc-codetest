package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static java.util.Comparator.comparing;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto[] sort(CustomerDto[] customers) {
        Arrays.sort(customers, comparing(CustomerDto::getDueTime));
        return customers;
    }
}