package Client;

import javax.swing.*;
import java.util.Arrays;

public class AuthWindow extends JFrame {
    private JPanel authPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton authButton;
    private JButton registrationButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel systemInfo;
//    private ClientController clientController;

    public AuthWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setContentPane(authPanel);
        authButton.addActionListener(e -> sendAuthTokens());
        registrationButton.addActionListener(e -> goToRegistration());
        setVisible(true);
    }

    private void sendAuthTokens() {
        String authTokens = "/auth " + loginField.getText() + " " + Arrays.hashCode(passwordField.getPassword());
        // send Tokens by ClientController
    }

    private void goToRegistration() {
        setVisible(false);
        new RegistrationWindow();
    }

    public void setSystemInfo (String info) {
        systemInfo.setText(info);
    }
}
