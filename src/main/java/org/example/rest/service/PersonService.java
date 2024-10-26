package org.example.rest.service;

import java.util.Map;

/**
 * Сервис по работе с персонами
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
public interface PersonService {

    /**
     * Метод выполняет отправку объекта Person
     *
     * @param person персона
     */
    void send(Map<String, Object> person);

    /**
     * Метод выполняет получение объекта Person
     *
     * @param person персона
     */
    void receive(Map<String, Object> person);
}
