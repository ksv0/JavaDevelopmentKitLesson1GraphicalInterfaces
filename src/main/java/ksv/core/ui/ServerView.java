package ksv.core.ui;

import ksv.core.servermvc.ServerServices;

import javax.swing.*;
import java.awt.*;

public class ServerView extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;

    ServerServices services;

    JTextArea log;

    JButton buttonStart, buttonStop, buttonExit;

    public ServerView() {
        super("Chat server");
        services = new ServerServices(this);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        getContentPane().setLayout(new BorderLayout());

        createLogPanel();
        createButtons();

        pack();

        this.setVisible(true);
    }

    private void createLogPanel() {
        log = new JTextArea(services.getLogsAsString());
        log.setMinimumSize(new Dimension(300, 300));
        log.setEditable(false);
        add(log, BorderLayout.CENTER);
    }

    private void createButtons() {
        JPanel buttons = new JPanel();

        buttons.setLayout(new GridLayout(1, 2));

        buttonStart = new JButton("Start server");
        buttonStart.addActionListener(e -> {
            services.startServer();
        });
        buttonStop = new JButton("Stop server");
        buttonStop.addActionListener(e -> {
            services.stopServer();
        });
        buttonExit = new JButton("Exit");
        buttonExit.addActionListener(e -> {
            System.exit(0);
        });

        buttons.add(buttonStart);
        buttons.add(buttonStop);
        buttons.add(buttonExit);

        add(buttons, BorderLayout.SOUTH);
    }

    public void addLog(String s) {
        log.append(s + "\n");


    }

    public ServerServices getServices() {
        return services;
    }
}
