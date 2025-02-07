package org.matt.dev.codes.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic testTopic() {
        return TopicBuilder.name("task_topic")
                .build();
    }
    
    @Bean
    public NewTopic jsonTopic() {
        return TopicBuilder.name("task_json_topic")
                .build();
    }
}