package com.example.demo.service;


import com.example.demo.dto.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    private static final ObjectMapper objectMapper = initObjectMapper();

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void sort() throws IOException {

        File json = ResourceUtils.getFile("classpath:10_customers.json");
        CustomerDto[] customers = objectMapper.readValue(json, CustomerDto[].class);
        customerService.sort(customers);

        Iterator<CustomerDto> iterator = Arrays.stream(customers).iterator();
        CustomerDto current = iterator.next();
        assertNotNull(current);

        while (iterator.hasNext()) {
            CustomerDto next = iterator.next();
            assertThat(current.getDueTime()).isBeforeOrEqualTo(next.getDueTime());
            current = next;
        }
    }

    private static ObjectMapper initObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}