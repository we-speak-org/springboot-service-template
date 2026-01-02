package org.wespeak.template.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Example Kafka consumer
 * Replace with your own event consumers according to kafka-events.md
 */
@Component
@Slf4j
public class ExampleEventConsumer {

    @KafkaListener(
            topics = "example.events",
            groupId = "template-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeExampleEvent(Map<String, Object> event) {
        log.info("Received event: {}", event);
        
        String eventType = (String) event.get("eventType");
        @SuppressWarnings("unchecked")
        Map<String, Object> payload = (Map<String, Object>) event.get("payload");
        
        switch (eventType) {
            case "example.created":
                handleExampleCreated(payload);
                break;
            case "example.deleted":
                handleExampleDeleted(payload);
                break;
            default:
                log.warn("Unknown event type: {}", eventType);
        }
    }

    private void handleExampleCreated(Map<String, Object> payload) {
        log.info("Handling example.created event: {}", payload);
        // Implement your business logic here
    }

    private void handleExampleDeleted(Map<String, Object> payload) {
        log.info("Handling example.deleted event: {}", payload);
        // Implement your business logic here
    }
}
