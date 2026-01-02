package org.wespeak.template.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.wespeak.template.model.entity.ExampleEntity;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExampleEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "example.events";

    public void publishExampleCreated(ExampleEntity entity) {
        Map<String, Object> event = buildEvent("example.created", entity);
        send(entity.getId(), event);
    }

    public void publishExampleDeleted(ExampleEntity entity) {
        Map<String, Object> event = buildEvent("example.deleted", entity);
        send(entity.getId(), event);
    }

    private Map<String, Object> buildEvent(String eventType, ExampleEntity entity) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", entity.getId());
        payload.put("code", entity.getCode());
        payload.put("name", entity.getName());

        Map<String, Object> event = new HashMap<>();
        event.put("eventType", eventType);
        event.put("version", "1.0");
        event.put("timestamp", Instant.now().toString());
        event.put("payload", payload);
        
        Map<String, String> metadata = new HashMap<>();
        metadata.put("source", "template-service");
        event.put("metadata", metadata);

        return event;
    }

    private void send(String key, Map<String, Object> event) {
        kafkaTemplate.send(TOPIC, key, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to send event to topic {}: {}", TOPIC, event, ex);
                    } else {
                        log.info("Event sent successfully to topic {}: {}", TOPIC, event.get("eventType"));
                    }
                });
    }
}
