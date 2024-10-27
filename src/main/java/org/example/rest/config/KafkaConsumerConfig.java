package org.example.rest.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация для Kafka Consumer
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@Configuration
public class KafkaConsumerConfig {

    /**
     * Адрес сервера Kafka
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Наименование группы потребителей Kafka
     */
    @Value("${spring.kafka.consumer-group}")
    private String consumerGroups;

    /**
     * Метод создает бин фабрики потребителей.
     *
     * @return возвращает фабрику потребителей параметризованную String
     */
    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroups);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new StringDeserializer());
    }

    /**
     * Метод создает бин фабрики контейнеров слушателя Kafka.
     *
     * @return возвращает фабрику контейнеров слушателя Kafka параметризованную String
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }
}