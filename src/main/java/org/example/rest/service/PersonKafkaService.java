package org.example.rest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rest.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса по работе с персонами с использованием Kafka
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see org.example.rest.service.PersonService
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PersonKafkaService implements PersonService {

    /**
     * Наименование топика
     */
    @Value("${spring.kafka.topic}")
    private String topic;

    /**
     * Объект для доступа к методам KafkaTemplate<String, Person>
     */
    private final KafkaTemplate<String, Person> kafkaTemplate;

    /**
     * Метод выполняет отправку объекта Person в топик Kafka
     *
     * @param person персона
     */
    @Override
    public void send(Person person) {
        log.info("Вызов метода send() класса PersonService");
        Person changedPerson = processing(person);
        kafkaTemplate.send(topic, changedPerson);
        log.info("В Kafka отправлен объект: {}, topic: {}", changedPerson, topic);
    }

    /**
     * Метод выполняет обработку Person
     *
     * @param person персона
     * @return person измененный объект
     */
    private Person processing(Person person) {
        person.setAge(96);
        person.getMother().setAge(96);
        return person;
    }

    /**
     * Метод выполняет получение объекта Person из топика Kafka
     *
     * @param person персона
     */
    @KafkaListener(topics = "${spring.kafka.topic}", containerFactory = "personKafkaListenerContainerFactory")
    @Override
    public void receive(Person person) {
        log.info("Вызов метода receive() класса PersonService");
        log.info("Из Kafka получен объект: {}, topic: {}", person, topic);
    }
}