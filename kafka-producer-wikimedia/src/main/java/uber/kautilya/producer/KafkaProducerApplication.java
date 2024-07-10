package uber.kautilya.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uber.kautilya.producer.service.WikimediaEventProducerService;

@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {
    @Autowired
    private WikimediaEventProducerService eventProducerService;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        eventProducerService.sendMessage();
    }
}
