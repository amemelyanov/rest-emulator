package org.example.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Модель данных персона
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    /**
     * Имя
     */
    private String name;

    /**
     * Возраст
     */
    private Integer age;

    /**
     * Мама
     */
    private Mother mother;

    /**
     * Дети
     */
    private List<String> children;

    /**
     * Статус супружества
     */
    private boolean married;

    /**
     * Собака
     */
    private String dog;
}