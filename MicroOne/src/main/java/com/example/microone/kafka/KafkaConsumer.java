package com.example.microone.kafka;

import com.example.microone.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "message", groupId = "consumer")
    public void listen(Message message) {
        log.warn("Received Message in group 'consumer': " + message.toString());
    }
}
