package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.Message;
import org.example.backend.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

/**
 * Реализация сервиса по работе с сообщениями с использованием Redis
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see org.example.backend.service.MessageService
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageRedisService implements MessageService {

    /**
     * Объект для доступа к методам MessageRepository
     */
    private final MessageRepository messageRepository;

    /**
     * Метод выполняет сохранение объекта Message в Redis
     *
     * @param message сообщение
     * @return message сохраненный объект
     */
    @Override
    public Message save(Message message) {
        log.info("Вызов метода save() класса MessageRedisService");
        processingBeforeRedis(message);
        log.info("В Redis сохраняется объект: {}", message);

        Message savedMessage = messageRepository.save(message);
        workWithRedis(message);

        return savedMessage;
    }

    /**
     * Метод выполняет поиск объекта Message в Redis
     * по id
     *
     * @param id идентификатор сообщения
     * @return message найденное в репозитории сообщение
     */
    @Override
    public Message findById(Long id) {
        log.info("Вызов метода findById() класса MessageRedisService");
        Message messageFromRedis = null;
        try {
            messageFromRedis = messageRepository.findById(id).orElseThrow(
                    NotFoundException::new
            );
        } catch (NotFoundException e) {
            log.info("Объект с id: {} не найден", id);
        }
        processingAfterRedis(messageFromRedis);
        log.info("Из Redis получен объект: {}", messageFromRedis);
        return messageFromRedis;
    }

    /**
     * Метод выполняет получение всех объектов Message из Redis
     *
     * @return messages список сообщений
     */
    @Override
    public List<Message> findAll() {
        log.info("Вызов метода findAll() класса MessageRedisService");
        List<Message> messages = StreamSupport.stream(messageRepository.findAll().spliterator(), false).toList();
        log.info("Из Redis получены все объекты: {}", messages);
        return messages;
    }

    /**
     * Метод выполняет имитацию работы с Redis получение
     * объекта по id и получение всех объектов
     *
     * @param message сообщение
     */
    private void workWithRedis(Message message) {
        log.info("Вызов метода workWithRedis() класса MessageRedisService");

        findById(message.getId());
        findAll();
    }

    /**
     * Метод выполняет обработку Message до отправления в Redis
     *
     * @param message сообщение
     */
    private void processingBeforeRedis(Message message) {
        message.setText("Это сообщение до сохранения в Redis");
    }

    /**
     * Метод выполняет обработку Message после получения из Redis
     *
     * @param message сообщение
     */
    private void processingAfterRedis(Message message) {
        message.setText("Это сообщение после получения из Redis");
    }
}