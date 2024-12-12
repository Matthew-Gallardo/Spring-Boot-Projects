package org.matt.dev.codes.kafka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.matt.dev.codes.model.Task;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaJsonProducer {

    private final KafkaTemplate<String, Task> kafkaTemplate;

    public KafkaJsonProducer(KafkaTemplate<String, Task> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Task task) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        kafkaTemplate.send(topic, task);
        log.info("JSON Produced message at {}: {}", timestamp, task);
    }
}