package org.example.rest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rest.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса по работе с сообщениями с использованием Kafka
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see org.example.rest.service.MessageService
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

    /**
     * Объект для доступа к методам KafkaTemplate<String, Message>
     */
    private final KafkaTemplate<String, Message> kafkaTemplate;

    /**
     * Метод выполняет отправку объекта Message в топик Kafka
     *
     * @param message сообщение
     */
    @Override
    public void send(Message message) {
        log.info("Вызов метода send() класса MessageService");
        Message changedMessage = processingBeforeKafka(message);
        kafkaTemplate.send(topic, changedMessage);
        log.info("В Kafka отправлен объект: {}, topic: {}", changedMessage, topic);
    }

    /**
     * Метод выполняет обработку Message до отправления в Kafka
     *
     * @param message сообщение
     * @return message измененный объект
     */
    private Message processingBeforeKafka(Message message) {
        message.setText("Это сообщение до отправки в Kafka");
        return message;
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