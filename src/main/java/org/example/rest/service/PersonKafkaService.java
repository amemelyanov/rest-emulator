package org.example.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rest.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Объект для доступа к методам ObjectMapper
     */
    private final ObjectMapper objectMapper;

    /**
     * Объект для доступа к методам KafkaTemplate<String, String>
     */
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Метод выполняет отправку объекта String в топик Kafka
     *
     * @param person персона
     */
    @Override
    public void send(String person) {
        log.info("Вызов метода send() класса PersonService");
        String changedPerson = processing(person);
        kafkaTemplate.send(topic, changedPerson);
        log.info("В Kafka отправлен объект: {}, topic: {}", changedPerson, topic);
    }

    /**
     * Метод выполняет обработку Person
     *
     * @param person персона
     * @return person измененный объект
     */
    private String processing(String person) {
        Pattern pattern = Pattern.compile("\"age\": \\d+");
        Matcher matcher = pattern.matcher(person);
        return matcher.replaceAll("\"age\": 96");
    }

    /**
     * Метод выполняет получение объекта String (person) из топика Kafka
     * и преобразует его в объект Person
     *
     * @param person персона
     */
    @KafkaListener(topics = "${spring.kafka.topic}", containerFactory = "stringKafkaListenerContainerFactory")
    @Override
    public void receive(String person) throws JsonProcessingException {
        Person personFromKafka = objectMapper.readValue(person, Person.class);
        log.info("Вызов метода receive() класса PersonService");
        log.info("Из Kafka получен объект: {}, topic: {}", person, topic);
    }
}