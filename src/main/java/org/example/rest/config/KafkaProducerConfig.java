package org.example.rest.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.rest.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация для Kafka Producer
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * Адрес сервера Kafka
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Метод создает бин доступа к методам
     *
     * @return возвращает объект для доступа к методам Kafka Producer
     */
    @Bean
    public KafkaTemplate<String, Person> personKafkaTemplate() {
        return new KafkaTemplate<>(personProducerFactory());
    }

    /**
     * Метод создает бин фабрики производителей.
     *
     * @return возвращает фабрику производителей параметризованную Person
     */
    @Bean
    public ProducerFactory<String, Person> personProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

}