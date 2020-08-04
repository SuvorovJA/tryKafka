package com.spec.service;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileReaderService {

    private static final String FILE = "/messages.txt";
    private static final AtomicBoolean stop = new AtomicBoolean(false);
    private static final MessageService messageService = new MessageService();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public void readFile(JTextArea area) {

        stop.set(false);
        executorService.submit(() -> {
            try (InputStream in = getClass().getResourceAsStream(FILE);
                 BufferedReader reader = new BufferedReader(
                         new InputStreamReader(in, StandardCharsets.UTF_8));
            ) {
                String line = reader.readLine();
                while (!stop.get() && line != null) {
                    System.out.println(stop.get() + line);
                    area.setText(line);
                    area.update(area.getGraphics());
                    messageService.sendMessage(line);
                    line = reader.readLine();
                    TimeUnit.MILLISECONDS.sleep(700);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                if (e instanceof InterruptedException){
                    Thread.currentThread().interrupt();
                }
            }

        });
    }

    public void stopRead() {
        stop.set(true);
    }
}
