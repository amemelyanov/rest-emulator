package org.example.rest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rest.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    @Value("${spring.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, Person> kafkaTemplate;

    public void send(Person person) {
        log.info("Вызов метода send() класса PersonService");
        processing(person);
        kafkaTemplate.send(topic, person);
        log.info("В Kafka отправлен объект: {}, topic: {}", person, topic);
    }

    private static void processing(Person person) {
        person.setAge(96);
        person.getMother().setAge(96);
    }

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "messages-group",
            containerFactory = "personKafkaListenerContainerFactory")
    public void receive(Person person) {
        log.info("Вызов метода receive() класса PersonService");
        log.info("Из Kafka получен объект: {}, topic: {}", person, topic);
    }
}