package com.marciobueno.aos.helloweb.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gatinhos")
public class GatinhoController {

    @Value("${cat.api.key}")
    private String apiKey;

    private static final String CAT_API_URL = "https://api.thecatapi.com/v1/images/search";

    @GetMapping
    public ResponseEntity<String> getGatinho() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-api-key", apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                CAT_API_URL,
                HttpMethod.GET,
                entity,
                String.class
            );

            return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .body(response.getBody());
                
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\":\"Falha ao buscar gatinhos: " + e.getMessage() + "\"}");
        }
    }
}