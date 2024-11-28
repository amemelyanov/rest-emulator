package org.example.rest.service;

import org.example.rest.model.Message;

/**
 * Сервис по работе с сообщениями
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see org.example.rest.model.Message
 */
public interface MessageService {

    /**
     * Метод выполняет отправку объекта Message
     *
     * @param message сообщение
     */
    void send(Message message);

    /**
     * Метод выполняет получение объекта Message
     *
     * @param message сообщение
     */
    void receive(Message message);
}
