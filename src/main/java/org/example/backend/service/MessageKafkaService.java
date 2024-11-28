package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса по работе с сообщениями с использованием Kafka
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see MessageKafkaService
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageKafkaService implements MessageService {

    /**
     * Наименование топика
     */
    @Value("${spring.kafka.topic}")
    private String topic;
//    private final MessageRepository messageRepository;

    /**
     * Метод выполняет получение объекта Message из топика Kafka
     *
     * @param message сообщение
     */
    @KafkaListener(topics = "${spring.kafka.topic}", containerFactory = "messageKafkaListenerContainerFactory")
    public void receive(Message message) {
        log.info("Вызов метода receive() класса MessageService");
        processingAfterKafka(message);
        log.info("Из Kafka получен объект: {}, topic: {}", message, topic);
//        messageRepository.save(message);
    }

    /**
     * Метод выполняет обработку Message после получения из Kafka
     *
     * @param message сообщение
     * @return message измененный объект
     */
    private Message processingAfterKafka(Message message) {
        message.setText("Это сообщение после получения из Kafka");
        return message;
    }
}