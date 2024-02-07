package com.example.postcode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AddressServiceTest {

    @Test
    public void getAddressTest() {

        AddressService addressService = new AddressService();
        addressService.getAddress("2221ET", "104");

    }

}