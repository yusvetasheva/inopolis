package com.example.controller;

import com.example.dto.KafkaMessage;
import com.example.kafka.KafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/send")
    public String sendMessages(@RequestParam(defaultValue = "10") int count) {
        for (int i = 0; i < count; i++) {
            KafkaMessage message = new KafkaMessage();
            message.setId((long) i);
            message.setMessage("Message " + i);
            message.setSendTime(LocalDateTime.now());
            kafkaProducer.sendMessage(message);
        }
        return "Sent " + count + " messages to Kafka";
    }
}
