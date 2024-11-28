package org.example.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель данных сообщение
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Текст сообщения
     */
    private String text;
}