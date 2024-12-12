package org.matt.dev.codes.kafka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "task_topic", groupId = "group_id")
    public void consume(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("Consumed message at {}: {}", timestamp, message);
    }
}