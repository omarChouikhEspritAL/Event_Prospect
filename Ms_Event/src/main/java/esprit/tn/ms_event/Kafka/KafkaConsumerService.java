package esprit.tn.ms_event.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "event-topic", groupId = "event-group")
    public void consume(String message) {
        System.out.println("🔔 Message reçu depuis Kafka: " + message);
        // Tu peux ici déclencher une action : ex. log, mise à jour, etc.
    }
}
