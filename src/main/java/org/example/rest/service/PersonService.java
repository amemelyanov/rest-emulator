package org.example.rest.service;

import org.example.rest.model.Person;

/**
 * Сервис по работе с персонами
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see org.example.rest.model.Person
 */
public interface PersonService {

    /**
     * Метод выполняет отправку объекта Person
     *
     * @param person персона
     */
    void send(Person person);

    /**
     * Метод выполняет получение объекта Person
     *
     * @param person персона
     */
    void receive(Person person);
}
