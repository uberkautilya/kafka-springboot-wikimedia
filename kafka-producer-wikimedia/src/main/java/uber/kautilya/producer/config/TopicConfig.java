package uber.kautilya.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("wikimedia_events")
                .partitions(2)
                .build();
    }
}
