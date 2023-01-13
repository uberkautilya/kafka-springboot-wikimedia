package uber.kautilya.wikimediaconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerDatabaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDatabaseService.class);

    @KafkaListener(topics = "wikimedia_events", groupId = "myConsumerGroup")
    public void consume(String eventMessage) {
        LOGGER.info("Consumer eventMessage: " + eventMessage);
    }
}
