package org.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * Модель данных сообщение
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@RedisHash
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    /**
     * Идентификатор
     */
    @Id
    private Long id;

    /**
     * Текст сообщения
     */
    private String text;
}