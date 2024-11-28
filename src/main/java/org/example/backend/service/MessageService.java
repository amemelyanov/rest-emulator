package org.example.backend.service;

import org.example.backend.model.Message;

/**
 * Сервис по работе с сообщениями
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see Message
 */
public interface MessageService {

    /**
     * Метод выполняет получение объекта Message
     *
     * @param message сообщение
     */
    void receive(Message message);
}
