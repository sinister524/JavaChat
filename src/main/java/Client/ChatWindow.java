package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
    private ClientController clientController;

    protected ChatWindow(ClientController clientController) {
        this.clientController = clientController;
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setContentPane(rootPanel);
        onlineNow.setText("<html>Сейчас в<br>чате: </html>");
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());
        message = "";
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                clientController.close();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        setVisible(true);
    }


    private void sendMessage () {
        message = message + messageField.getText();
        this.clientController.sendMessage(message);
        message = "";
        messageField.setText("");
    }
    private void makeMessagePrivate (String clientName) {
        if (!message.startsWith("/w")) {
            message = "/w " + clientName + " " + message;
            sendButton.setText("<html>Отправить<br>" + clientName + "</html>");
        } else {
            message = "";
            sendButton.setText("Отправить");
        }
        sendButton.revalidate();
    }

    protected void createClientList (String onlineClients) {
        String [] clients = onlineClients.split(" ");
        rightPanel.removeAll();
        for (int i = 1; i < clients.length; i++) {
            JLabel clientNick = new JLabel(clients[i]);
            clientNick.setPreferredSize(new Dimension(100, 20));
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
        rightPanel.revalidate();
    }
    protected void acceptMessage (String message) {
        chatMessages.append(message + "\n");
    }
}
