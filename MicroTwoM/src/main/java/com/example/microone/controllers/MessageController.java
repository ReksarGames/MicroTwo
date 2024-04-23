package com.example.microone.controllers;

import com.example.microone.dto.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/message-receiver")
class MessageController {
    private final WebClient webClient;

    public MessageController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:9000").build(); // предполагаем, что первый сервис находится на порту 9000
    }

    @PostMapping("/receive")
    public ResponseEntity<MessageDTO> receiveMessage(@RequestBody MessageDTO responseMessage) {
        return ResponseEntity.ok(responseMessage);
    }

}
