package com.spec.ui;

import com.spec.service.FileReaderService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public final class MWindow extends JFrame {

    private final JTextArea area = new JTextArea();
    JButton startButton = new JButton("START SEND");
    JButton stopButton = new JButton("STOP SEND");
    FileReaderService readerService = new FileReaderService();

    public MWindow() {
        this.setTitle("SENDER");
        this.setBounds(10, 10, 600, 600);
        //
        this.getContentPane().add(createPanel());
        //
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JPanel createPanel() {

        JPanel p = new JPanel();

        area.setRows(31);
        area.setColumns(50);
        area.setLineWrap(true);
//        area.setEnabled(false);

        startButton.addActionListener(
                new StartActionListener());
        stopButton.addActionListener(
                new StopActionListener());
        stopButton.setEnabled(false);

        p.add(area);
        p.add(startButton);
        p.add(stopButton);
        return p;
    }

    private class StopActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            readerService.stopRead();
        }
    }

    private class StartActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            readerService.readFile(area);
        }
    }

}
