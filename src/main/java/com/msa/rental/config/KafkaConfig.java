package com.msa.rental.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
//    @Bean
//    public ProducerFactory<String, ItemRented> producerFactory()
//    {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//    @Bean
//    public KafkaTemplate kafkaTemplate()
//    {
//        return new KafkaTemplate<>(producerFactory());
//    }
}
