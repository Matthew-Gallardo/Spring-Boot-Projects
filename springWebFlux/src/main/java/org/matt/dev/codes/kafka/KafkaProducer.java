package org.matt.dev.codes.kafka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.matt.dev.codes.dto.UserDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String topic, UserDTO userDTO) {
        try {
            String message = objectMapper.writeValueAsString(userDTO);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            kafkaTemplate.send(topic, message);
            log.info("Produced message at {}: {}", timestamp, message);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize UserDTO", e);
        }
    }
}