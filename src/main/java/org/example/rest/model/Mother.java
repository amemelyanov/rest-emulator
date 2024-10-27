package org.example.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель данных мама
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mother {

    /**
     * Имя
     */
    private String name;

    /**
     * Возраст
     */
    private Integer age;
}