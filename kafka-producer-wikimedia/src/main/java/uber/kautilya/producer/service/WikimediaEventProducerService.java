package uber.kautilya.producer.service;


import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uber.kautilya.producer.eventhandler.WikimediaEventHandler;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaEventProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaEventProducerService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Needn't autowire the kafkaTemplate as this class which is a @Service component has only one constructor, which is parameterized
     * Spring will auto inject the dependency in such cases
     *
     * @param kafkaTemplate - spring auto-injects it as it is the only constructor
     */
    public WikimediaEventProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        //Read from an event source - here wikimedia stream
        EventHandler eventHandler = new WikimediaEventHandler(kafkaTemplate, "wikimedia_events");
        URI uri = URI.create("https://stream.wikimedia.org/v2/stream/recentchange");

        EventSource.Builder eventSourceBuilder = new EventSource.Builder(eventHandler, uri);

        try (EventSource eventSource = eventSourceBuilder.build()) {
            eventSource.start();
        }

        TimeUnit.MINUTES.sleep(10);
    }
}


