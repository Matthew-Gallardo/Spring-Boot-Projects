package org.matt.dev.codes.kafka;


import org.matt.dev.codes.model.Task;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaJsonConsumer {

    @KafkaListener(topics = "task_json_topic", groupId = "group_id")
    public void consume(Task task) {
        log.info("JSON Consumed message: {}", task);
    }
}