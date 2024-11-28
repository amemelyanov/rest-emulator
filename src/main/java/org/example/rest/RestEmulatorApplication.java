package org.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс для запуска приложения
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = "org.example")
public class RestEmulatorApplication {

    /**
     * Метод выполняет запуск приложения
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(RestEmulatorApplication.class, args);
    }

}
