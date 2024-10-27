package org.example.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Сервис по работе с персонами
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see org.example.rest.model.Person
 */
public interface PersonService {

    /**
     * Метод выполняет отправку объекта String
     *
     * @param person персона
     */
    void send(String person);

    /**
     * Метод выполняет получение объекта String
     *
     * @param person персона
     */
    void receive(String person) throws JsonProcessingException;
}
