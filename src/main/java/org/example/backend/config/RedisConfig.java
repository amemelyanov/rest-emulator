package org.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Конфигурация для Redis client
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@Configuration
public class RedisConfig {

    /**
     * Метод создает бин фабрики для подключения к Redis.
     *
     * @return возвращает фабрику подключений
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    /**
     * Метод создает бин для работы с репозиторием.
     *
     * @return возвращает бин для работы с репозиторием
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
