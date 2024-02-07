package com.example.postcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Value("${application.baseURL}")
    private String baseURL;

    @Value("${application.accessToken}")
    private String accessToken;

    @Cacheable("addresses")
    public ResponseEntity<String> getAddress(String zipCode, String houseNumber) {
        RestTemplate restTemplate = new RestTemplate();

        String URI = UriComponentsBuilder.fromUriString(baseURL)
                .queryParam("postcode", zipCode)
                .queryParam("number", houseNumber)
                .build().toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(URI, HttpMethod.GET, entity, String.class);

        logger.info("Response received from API: {}", response.getBody());

        return response;
    }

}
