package org.example.backend.service;

import org.example.backend.model.Message;

import java.util.List;

/**
 * Сервис по работе с сообщениями
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see Message
 */
public interface MessageService {

    /**
     * Метод выполняет сохранение объекта Message в Redis
     *
     * @param message сообщение
     */
    Message save(Message message);

    /**
     * Метод выполняет поиск объекта Message в Redis
     * по id
     *
     * @param id идентификатор сообщения
     */
    Message findById(Long id);

    /**
     * Метод выполняет получение всех объектов Message из Redis
     *
     * @return messages список сообщений
     */
    List<Message> findAll();
}
