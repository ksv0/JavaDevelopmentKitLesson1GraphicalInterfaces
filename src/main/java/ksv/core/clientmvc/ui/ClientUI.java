package ksv.core.clientmvc.ui;

import ksv.core.clientmvc.services.ClientServices;
import ksv.core.clientmvc.services.ClientServicesInterface;
import ksv.core.servermvc.services.ServerServices;
import ksv.core.servermvc.services.ServerServicesInterface;

import javax.swing.*;
import java.awt.*;

public class ClientUI extends JFrame implements ClientView{
    private JPanel textFields;
    private JTextField loginField, ipField, portField, chatInputField;
    private JPasswordField passwordField;
    private JPanel buttons;
    private JButton loginButton;
    private JButton sendButton ;
    private JButton exitButton;

    private JScrollPane scrollPane;
    private JTextArea log;
    private JPanel chatInput;

    private ClientServicesInterface services;

    public ClientUI(ServerServicesInterface serverServices) {
        super("Client");
        services = new ClientServices(this, serverServices);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        createTextFields();

        createLogArea();
        createButtons();


        this.pack();
        this.setVisible(true);
    }

    private void createLogArea() {
        log = new JTextArea();
        log.setEditable(false);
        scrollPane = new JScrollPane(log);
        scrollPane.setMinimumSize(new Dimension(300, 300));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createTextFields() {
        textFields = new JPanel();
        textFields.setLayout(new GridLayout(4, 2));

        loginField = new JTextField(10);
        passwordField = new JPasswordField(10);
        ipField = new JTextField("127.0.0.1", 15);
        portField = new JTextField("0001", 4);

        textFields.add(new JLabel("Login:"));
        textFields.add(loginField);


        textFields.add(new JLabel("Password:"));
        textFields.add(passwordField);


        textFields.add(new JLabel("IP:"));
        textFields.add(ipField);

        textFields.add(new JLabel("Port:"));
        textFields.add(portField);

        add(textFields, BorderLayout.NORTH);
    }

    private void createButtons() {

        buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 2));

        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");

        loginButton.addActionListener(e -> {
            services.sendLogin(loginField.getText(), passwordField.getText());
            textFields.setVisible(!services.isLoggedIn());
            buttons.setVisible(textFields.isVisible());
            if (!textFields.isVisible()) {
                createChatInput();
            }
        });
        exitButton.addActionListener(e -> {
            services.logout();
            System.exit(0);
        });

        buttons.add(loginButton);
        buttons.add(exitButton);

        add(buttons, BorderLayout.SOUTH);
    }

    private void createChatInput() {

        chatInput = new JPanel();
        chatInput.setLayout(new GridLayout(1, 2));

        chatInputField = new JTextField(20);
        sendButton = new JButton("Send");

        sendButton.addActionListener(e -> {
            services.sendMessage(chatInputField.getText());
            chatInputField.setText("");
        });

        chatInput.add(chatInputField);
        chatInput.add(sendButton);

        add(chatInput, BorderLayout.SOUTH);


    }

    public void addLog(String log) {
        this.log.append(log + "\n");

    }

    public void toTitle() {
        textFields.setVisible(true);
        createButtons();
        chatInput.setVisible(false);
    }
}
