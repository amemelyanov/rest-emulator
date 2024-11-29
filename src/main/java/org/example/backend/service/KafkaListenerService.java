package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Сервис слушатель Kafka
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see MessageRedisService
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaListenerService {
    /**
     * Наименование топика
     */
    @Value("${spring.kafka.topic}")
    private String topic;

    /**
     * Объект для доступа к методам MessageService
     */
    private final MessageService messageService;

    /**
     * Метод выполняет получение объекта Message из топика Kafka
     *
     * @param message сообщение
     */
    @KafkaListener(topics = "${spring.kafka.topic}", containerFactory = "messageKafkaListenerContainerFactory")
    public void receive(Message message) {
        log.info("Вызов метода receive() класса KafkaListenerService");
        processingAfterKafka(message);
        log.info("Из Kafka получен объект: {}, topic: {}", message, topic);
        messageService.save(message);
    }

    /**
     * Метод выполняет обработку Message после получения из Kafka
     *
     * @param message сообщение
     */
    private void processingAfterKafka(Message message) {
        message.setText("Это сообщение после получения из Kafka");
    }
}
