package org.example.rest.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.rest.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

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
     * @return возвращает фабрику потребителей параметризованную Message
     */
    @Bean
    public ConsumerFactory<String, Message> messageConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroups);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(Message.class));
    }

    /**
     * Метод создает бин фабрики контейнеров слушателя Kafka.
     *
     * @return возвращает фабрику контейнеров слушателя Kafka параметризованную Message
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> messageKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Message> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(messageConsumerFactory());
        return factory;
    }
}