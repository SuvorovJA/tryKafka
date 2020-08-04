package com.spec.service;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import javax.swing.*;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageReceiveService {
    private static final String TOPIC = "mymessages";
    private final JTextArea area;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public MessageReceiveService(JTextArea area) {
        this.area = area;
    }

    public void infiniteProcessMessages() {

        executorService.submit(() -> {
            Consumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(KafkaConfig.getConfig());
            kafkaConsumer.subscribe(Collections.singletonList(TOPIC));
            try {
                ConsumerRecords<String, String> consumerRecords;
                //noinspection InfiniteLoopStatement
                while (true) {
                    consumerRecords = kafkaConsumer.poll(Duration.of(1000L, ChronoUnit.MILLIS));
                    for (ConsumerRecord<String, String> record : consumerRecords) {
                        String key = record.key();
                        String val = record.value();
                        System.out.println("KEY " + key + " VALUE " + val);
                        area.setText(record.value());
                        area.update(area.getGraphics());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                kafkaConsumer.close();
            }
        });
    }

}
