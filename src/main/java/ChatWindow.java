import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {
    private JPanel rootPanel;
    private JPanel centralPanel;
    private JPanel downPanel;
    private JPanel leftPanel;
    private JPanel upPanel;
    private JButton sendButton;
    private JLabel nickLabel;
    private JTextField messageField;
    private JScrollPane clientList;
    private JLabel chatLabel;
    private JScrollPane chatScroll;
    private JTextArea textArea1;
    private JLabel onlineNow;

    public ChatWindow() throws HeadlessException {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setContentPane(rootPanel);
        setVisible(true);
        onlineNow.setText("<html>Сейчас в<br>чате: </html>");
    }
}
