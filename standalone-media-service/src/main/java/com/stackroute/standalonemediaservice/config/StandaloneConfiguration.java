package com.stackroute.standalonemediaservice.config;

import com.stackroute.standalonemediaservice.domain.StandaloneMedia;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class StandaloneConfiguration {

    private String address="127.0.0.1:9092";
    @Bean
    public ProducerFactory<StandaloneMedia, StandaloneMedia> producerFactory() {
        Map<Object, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(config);
    }
    @Bean
    public KafkaTemplate<StandaloneMedia, StandaloneMedia> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
