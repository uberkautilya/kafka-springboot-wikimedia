package uber.kautilya.wikimediaconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import uber.kautilya.wikimediaconsumer.entity.WikimediaEntity;
import uber.kautilya.wikimediaconsumer.repository.WikimediaRepository;

@Service
public class ConsumerDatabaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDatabaseService.class);
    private final WikimediaRepository wikimediaRepository;

    public ConsumerDatabaseService(WikimediaRepository wikimediaRepository) {
        this.wikimediaRepository = wikimediaRepository;
    }

    @KafkaListener(topics = "wikimedia_events", groupId = "myConsumerGroup")
    public void consume(String eventMessage) {
        /*LOGGER.info("Consumer eventMessage: " + eventMessage);*/
        WikimediaEntity wikimediaEntity = new WikimediaEntity();
        wikimediaEntity.setEventData(eventMessage);

        wikimediaRepository.save(wikimediaEntity);
    }
}
