
package com.spec.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;


public class MessageService {

    private static final String TOPIC = "mymessages";

    public ProducerRecord<String, String> crRecord(final String message) {
        return new ProducerRecord<String, String>(TOPIC, null, message);
    }

    public void sendMessage(String message) {
        System.out.println("SEND MESSAGE " + message);
        try (Producer<String, String> p = new KafkaProducer<>(KafkaConfig.getConfig())) {
            Future<RecordMetadata> send = p.send(crRecord(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
