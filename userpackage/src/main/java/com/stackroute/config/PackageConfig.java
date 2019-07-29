package com.stackroute.config;

import com.stackroute.domain.UserPackage;
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
public class PackageConfig {

    @Bean
    public ProducerFactory<UserPackage, UserPackage> producerFactory()
    {
        Map<Object,Object> config=new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);

        return new DefaultKafkaProducerFactory(config);

    }
    @Bean
    public KafkaTemplate<UserPackage, UserPackage> kafkaTemplate()
    {
        return new KafkaTemplate<UserPackage, UserPackage>(producerFactory());

    }
}
