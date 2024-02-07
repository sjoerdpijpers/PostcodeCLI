package com.example.postcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Scanner;

@Component
public class AddressClient implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AddressClient.class);

    private final AddressService addressService;

    public AddressClient(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("(type 'exit' to stop)");

        while(true) {
            String zipCode = getUserInput(scanner, "Postcode: ");
            String houseNumber = getUserInput(scanner, "Huisnummer: ");

            if ("exit".equalsIgnoreCase(zipCode) || "exit".equalsIgnoreCase(houseNumber)) {
                System.out.println("Exiting...");
                break;
            }

            try {
                ResponseEntity<String> response = addressService.getAddress(zipCode, houseNumber);
                System.out.println(response.getBody());
            } catch(HttpClientErrorException | HttpServerErrorException e) {
                logger.error("Something went wrong; Status Code {}", e.getStatusCode());
            }
        }

        System.exit(0);
    }

    private static String getUserInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}