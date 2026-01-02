package org.wespeak.template;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        properties = {
                "spring.autoconfigure.exclude=" +
                        "org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration," +
                        "org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration," +
                        "org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration," +
                        "org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration," +
                        "org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration"
        }
)
@ActiveProfiles("test")
class TemplateApplicationTests {

    @Test
    void contextLoads() {
        // Verify Spring context loads successfully without external dependencies
    }
}
