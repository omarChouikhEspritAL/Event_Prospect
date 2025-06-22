package esprit.tn.ms_prospect.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        try {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Message sent to Kafka topic='{}' | offset={} | message='{}'",
                            topic, result.getRecordMetadata().offset(), message);
                } else {
                    log.error("Failed to send message='{}' to topic='{}' | Error: {}",
                            message, topic, ex.getMessage(), ex);
                    // Tu peux ici déclencher une alerte ou retry selon les besoins
                }
            });
        } catch (Exception e) {
            log.error("Exception during Kafka send: {}", e.getMessage(), e);
        }
    }
}
