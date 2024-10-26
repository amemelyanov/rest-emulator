package org.example.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rest.model.Person;
import org.example.rest.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * Контроллер для работы с персонами через Rest API
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see org.example.rest.model.Person
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PersonController {

    /**
     * Объект для доступа к методам PersonService
     */
    private final PersonService personService;

    /**
     * Объект для доступа к методам ObjectMapper
     */
    private final ObjectMapper objectMapper;

    /**
     * Метод обрабатывает входящий POST запрос, отображает входящий JSON объект
     * в объект Person.
     *
     * @param person объект персоны
     */
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/person")
    public void send(@RequestBody Person person) {
        log.info("Вызов метода send() класса PersonController");
        log.info("На endpoint /person получен объект: {}", person);
        personService.send(person);
    }

    /**
     * Выполняет локальный (уровня контроллера) перехват исключений
     * Exception, в случае перехвата, выполняет логирование
     * и возвращает клиенту ответ с комментарием исключения.
     *
     * @param e перехваченное исключение
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