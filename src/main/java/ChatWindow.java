
import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {

    private JTextField message = new JTextField();
    private JTextField userName = new JTextField();
    private JTextArea chat = new JTextArea();
    private JPanel upPanel = new JPanel();
    private JPanel downPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel centrePanel = new JPanel();


    public ChatWindow() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setLayout(new BorderLayout());

        upPanel.setLayout(new BorderLayout());
        downPanel.setLayout(new BorderLayout());
        leftPanel.setLayout(new BorderLayout());
        centrePanel.setLayout(new BorderLayout());

        add(upPanel, BorderLayout.PAGE_START);
        add(downPanel, BorderLayout.PAGE_END);
        add(leftPanel, BorderLayout.LINE_START);
        add(centrePanel, BorderLayout.CENTER);

        setDownPanel();
        setLeftPanel();
        setCentrePanel();
        setUpPanel();

        setVisible(true);

    }

    private void setDownPanel() {
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
    }

    private void setLeftPanel () {
        leftPanel.setPreferredSize(new Dimension(100, 100));
        JTextArea user = new JTextArea();
        user.setEditable(false);
        user.append(userName.getText() + "\n");
//        leftPanel.add(user);
        JScrollPane usersOnline = new JScrollPane(user);
        leftPanel.add(usersOnline);
    }

    private void setCentrePanel () {
        chat.setEditable(false);
        JScrollPane chatHistory = new JScrollPane(chat);
        centrePanel.add(chatHistory);
    }

    private void setUpPanel () {
        JTextArea onlineNow = new JTextArea("Сейчас в чате:");
        onlineNow.setPreferredSize(new Dimension(100,20));
        onlineNow.setEnabled(false);
        JTextArea historyTitle = new JTextArea("История чата:");
        historyTitle.setEnabled(false);
        upPanel.add(onlineNow, BorderLayout.LINE_START);
        upPanel.add(historyTitle, BorderLayout.CENTER);
    }
}
