package org.wespeak.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Health", description = "Health and status endpoints")
public class HealthController {

    private final BuildProperties buildProperties;

    @GetMapping("/health")
    @Operation(summary = "Get service health status")
    public ResponseEntity<Map<String, Object>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", buildProperties.getName(),
                "version", buildProperties.getVersion(),
                "timestamp", Instant.now()
        ));
    }

    @GetMapping("/info")
    @Operation(summary = "Get service information")
    public ResponseEntity<Map<String, Object>> info() {
        return ResponseEntity.ok(Map.of(
                "name", buildProperties.getName(),
                "version", buildProperties.getVersion(),
                "buildTime", buildProperties.getTime(),
                "description", "WeSpeak Microservice Template"
        ));
    }
}
