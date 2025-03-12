package com.example.kafka;

import com.example.dto.KafkaMessage;
import org.springframework.kafka.core.KafkaTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(KafkaMessage message) {
        logger.info("Sending message: {}", message);
        kafkaTemplate.send("test-topic", message);
    }
}
