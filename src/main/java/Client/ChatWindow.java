package Client;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChatWindow extends JFrame {
    private JPanel rootPanel;
    private JPanel centralPanel;
    private JPanel downPanel;
    private JPanel rightPanel;
    private JPanel upPanel;
    private JButton sendButton;
    private JTextField messageField;
    private JScrollPane clientList;
    private JLabel chatLabel;
    private JScrollPane chatScroll;
    private JTextArea chatMessages;
    private JLabel onlineNow;
    private String message;

    public ChatWindow() {
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setContentPane(rootPanel);
        onlineNow.setText("<html>Сейчас в<br>чате: </html>");
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());
        setVisible(true);
    }


    private void sendMessage () {
        message = messageField + messageField.getText();
//        send by ClientController
        message = null;
        messageField.setText("");
    }
    private void makeMessagePrivate (String clientName) {
        if (!message.startsWith("/w")) {
            message = "/w " + clientName + " " + message;
            sendButton.setText("<html>Отправить<br>" + clientName + "</html>");
        } else {
            message = null;
            sendButton.setText("Отправить");
        }
        sendButton.revalidate();
    }

    public void createClientList (String onlineClients) {
        String [] clients = onlineClients.split(" ");
        rightPanel.removeAll();
        for (int i = 1; i < clients.length; i++) {
            JLabel clientNick = new JLabel(clients[i]);
            clientNick.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    makeMessagePrivate(clientNick.getText());
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            rightPanel.add(clientNick);
            clientList.revalidate();
        }
    }
    public void acceptMessage (String message) {
        chatMessages.append(message + "\n");
    }
}
