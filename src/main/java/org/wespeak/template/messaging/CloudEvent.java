package org.wespeak.template.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CloudEvent generic DTO for event consumption. This class follows the CloudEvents specification
 * and is used as a wrapper for all incoming Kafka messages.
 *
 * @param <T> The type of the event data payload
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudEvent<T> {

  /** Event type identifier (e.g., "user.registered") */
  @JsonProperty("eventType")
  private String eventType;

  /** CloudEvents spec version */
  @JsonProperty("specversion")
  @Builder.Default
  private String specVersion = "1.0";

  /** Event source (e.g., "auth-service") */
  @JsonProperty("source")
  private String source;

  /** Unique event identifier */
  @JsonProperty("id")
  private String id;

  /** Event timestamp */
  @JsonProperty("time")
  private Instant time;

  /** Content type of the data */
  @JsonProperty("datacontenttype")
  @Builder.Default
  private String dataContentType = "application/json";

  /** Event data payload */
  @JsonProperty("data")
  private T data;

  /** Optional correlation ID for tracking */
  @JsonProperty("correlationid")
  private String correlationId;

  /** Optional tenant identifier for multi-tenancy */
  @JsonProperty("tenantid")
  private String tenantId;
}
