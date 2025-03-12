package com.example.kafka;

import com.example.dto.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "test-topic", groupId = "group-id")
    public void listen(KafkaMessage message) {
        logger.info("Received Message: {}", message);
    }
}
