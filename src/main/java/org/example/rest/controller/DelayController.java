package org.example.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rest.model.Delay;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delay")
public class DelayController {

    private final ObjectMapper objectMapper;
    private Object lock = new Object();

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/thread")
    public void threadDelay(@RequestBody Delay delay) {
        try {
            Thread.sleep(delay.getDelay());
//            TimeUnit.MILLISECONDS.sleep(delay.getDelay());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/while")
    public void whileDelay(@RequestBody Delay delay) {
        long endTime = System.currentTimeMillis() + delay.getDelay();
        while (System.currentTimeMillis() < endTime) {
            boolean b = true;
        }
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/wait")
    public void waitDelay(@RequestBody Delay delay) throws InterruptedException {
       lock(delay);
    }

    private synchronized void lock(Delay delay) {
        try {
            wait(delay.getDelay());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
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