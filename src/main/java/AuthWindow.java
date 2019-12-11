import javax.swing.*;

public class AuthWindow extends JFrame {
    private JPanel authPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton authButton;
    private JButton registrationButton;
    private JLabel loginLabel;
    private JLabel passwordLable;
    private JLabel systemInfo;

    public AuthWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setContentPane(authPanel);
        setVisible(true);
    }
}
