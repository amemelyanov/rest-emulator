package org.example.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rest.model.Message;
import org.example.rest.service.MessageKafkaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * Контроллер для работы с сообщениями через Rest API
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see org.example.rest.model.Message
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {

    /**
     * Объект для доступа к методам MessageService
     */
    private final MessageKafkaService messageKafkaService;

    /**
     * Объект для доступа к методам ObjectMapper
     */
    private final ObjectMapper objectMapper;

    /**
     * Метод обрабатывает входящий POST запрос, отображает входящий JSON объект
     * в объект Message.
     *
     * @param message объект сообщения
     */
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping
    public void send(@RequestBody Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        log.info("Вызов метода send() класса MessageController");
        log.info("На endpoint \"/api/v1/messages\" получен объект: {}", message);
        messageKafkaService.send(message);
    }

    /**
     * Выполняет локальный (уровня контроллера) перехват исключений
     * IllegalArgumentException - некорректный объект в запросе клиента, в
     * случае перехвата, выполняет логирование и возвращает клиенту ответ
     * с комментарием исключения.
     *
     * @param e        перехваченное исключение
     * @param response ответ пользователю
     * @throws IOException проверяемое исключение метода
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void illegalArgumentException(Exception e, HttpServletResponse response)
            throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        log.error(e.getLocalizedMessage());
    }

    /**
     * Выполняет локальный (уровня контроллера) перехват исключений
     * Exception, в случае перехвата, выполняет логирование
     * и возвращает клиенту ответ с комментарием исключения.
     *
     * @param e        перехваченное исключение
     * @param response ответ пользователю
     * @throws IOException проверяемое исключение метода
     */
    @ExceptionHandler(value = {Exception.class})
    public void exceptionHandler(Exception e, HttpServletResponse response)
            throws IOException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        log.error(e.getLocalizedMessage());
    }
}