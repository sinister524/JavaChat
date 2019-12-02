package main.java;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {

    private JTextField message = new JTextField();
    private JTextField userName = new JTextField();
    private JTextArea chat = new JTextArea();


    public ChatWindow() {
        setTitle("Test Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setLayout(new BorderLayout());

        JPanel upPanel = new JPanel();
        JPanel downPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel centrePanel = new JPanel();

        upPanel.setLayout(new BorderLayout());
        downPanel.setLayout(new BorderLayout());
        leftPanel.setLayout(new BorderLayout());
        centrePanel.setLayout(new BorderLayout());

        add(upPanel, BorderLayout.PAGE_START);
        add(downPanel, BorderLayout.PAGE_END);
        add(leftPanel, BorderLayout.LINE_START);
        add(centrePanel, BorderLayout.CENTER);


//        Добавление элементов нанижнюю панель

        JButton send = new JButton("Отправить");
        send.setPreferredSize(new Dimension(100,60));
        try {
            String name = JOptionPane.showInputDialog ("Введи свой никнейм");
            if (!name.isEmpty())userName.setText(name);
            else userName.setText("Guest_1");
        } catch (NullPointerException e) {
            userName.setText("Guest_1");
        }


        userName.setEditable(false);
        userName.setPreferredSize(new Dimension(100,60));

        send.addActionListener(e -> AddStrings.sendText(userName,message, chat));
        message.addActionListener(e -> AddStrings.sendText(userName, message, chat));
        downPanel.add(userName, BorderLayout.LINE_START);
        downPanel.add(send, BorderLayout.LINE_END);
        downPanel.add(message, BorderLayout.CENTER);


//        Добавление элементов в левую панель


        JTextArea users = new JTextArea();
        users.setPreferredSize(new Dimension(100,100));
        users.setEditable(false);
        users.append(userName.getText() + "\n");
        JScrollPane usersOnline = new JScrollPane(users);
        leftPanel.add(usersOnline);


//        Добавление Эементов в центральную панель


        chat.setEditable(false);
        JScrollPane chatHistory = new JScrollPane(chat);
        centrePanel.add(chatHistory);

//        добавление элементов в верхнюю панель

        JTextArea onlineNow = new JTextArea("Сейчас в чате:");
        onlineNow.setPreferredSize(new Dimension(100,20));
        onlineNow.setEnabled(false);
        JTextArea historyTitle = new JTextArea("История чата:");
        historyTitle.setEnabled(false);
        upPanel.add(onlineNow, BorderLayout.LINE_START);
        upPanel.add(historyTitle, BorderLayout.CENTER);


        setVisible(true);

    }


}
