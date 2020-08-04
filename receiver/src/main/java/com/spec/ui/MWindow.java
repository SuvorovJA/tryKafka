package com.spec.ui;

import com.spec.service.MessageReceiveService;

import javax.swing.*;


public final class MWindow extends JFrame {

    private final JTextArea area = new JTextArea();

    public MWindow() {
        this.setTitle("RESEIVER");
        this.setBounds(10, 10, 600, 600);
        //
        this.getContentPane().add(createPanel());
        //
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        MessageReceiveService receiveService = new MessageReceiveService(area);
        receiveService.infiniteProcessMessages();
    }

    public JPanel createPanel() {

        JPanel p = new JPanel();

        area.setRows(31);
        area.setColumns(50);
        area.setLineWrap(true);

        p.add(area);

        return p;
    }

}
