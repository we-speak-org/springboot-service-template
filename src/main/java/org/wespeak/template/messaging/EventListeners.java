package org.wespeak.template.messaging;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wespeak.template.service.SampleService;

/**
 * Configuration for Kafka event listeners using Spring Cloud Stream functional style. Each
 * listener is a simple Consumer bean that receives a CloudEvent and delegates to a service.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class EventListeners {

  private final SampleService sampleService;

  /**
   * Example event listener for sample events. The function name must match the binding
   * configuration in application.properties: spring.cloud.function.definition=sampleEventListener
   *
   * @return Consumer that processes sample events
   */
  @Bean
  public Consumer<CloudEvent<SampleEventPayload>> sampleEventListener() {
    return event -> {
      log.info(
          "Received event: type={}, source={}, id={}",
          event.getEventType(),
          event.getSource(),
          event.getId());

      try {
        // Extract data and delegate to service layer
        SampleEventPayload data = event.getData();
        sampleService.handleSampleEvent(data);

        log.info("Successfully processed event: {}", event.getId());
      } catch (Exception e) {
        log.error("Error processing event: {}", event.getId(), e);
        // Re-throw to trigger retry or DLQ
        throw new RuntimeException("Failed to process event: " + event.getId(), e);
      }
    };
  }

  /** Example payload DTO for sample events */
  public record SampleEventPayload(String id, String name, String description) {}
}
