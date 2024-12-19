package org.matt.dev.codes.kafka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "user_topic", groupId = "group_id")
    public void consumer1(ConsumerRecord<String, String> record) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("Consumer 1 - Consumed message at {}: {} from partition {}", timestamp, record.value(), record.partition());
    }

    @KafkaListener(topics = "user_topic", groupId = "group_id")
    public void consumer2(ConsumerRecord<String, String> record) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("Consumer 2 - Consumed message at {}: {} from partition {}", timestamp, record.value(), record.partition());
    }

    @KafkaListener(topics = "user_topic", groupId = "group_id")
    public void consumer3(ConsumerRecord<String, String> record) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("Consumer 3 - Consumed message at {}: {} from partition {}", timestamp, record.value(), record.partition());
    }
}