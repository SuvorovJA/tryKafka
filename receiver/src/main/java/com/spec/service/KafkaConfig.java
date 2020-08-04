package com.spec.service;

import java.util.Properties;

public class KafkaConfig {

    public static Properties getConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        // props.put("acks", "all");
        // props.put("retries", 0);
        // props.put("batch.size", 16384);
        // props.put("linger.ms", 1);
        // props.put("buffer.memory", 33554432);
        props.put("group.id", "test-consumer-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
}
