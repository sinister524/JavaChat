package Client;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
    private ClientController clientController;

    protected AuthWindow(ClientController clientController) {
        this.clientController = clientController;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setContentPane(authPanel);
        authButton.addActionListener(e -> sendAuthTokens());
        registrationButton.addActionListener(e -> goToRegistration());
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

    private void sendAuthTokens() {
        String authTokens = "/auth " + loginField.getText() + " " + Arrays.hashCode(passwordField.getPassword());
        this.clientController.sendMessage(authTokens);
    }

    private void goToRegistration() {
        setVisible(false);
        this.clientController.setRegistrationWindow(new RegistrationWindow(this.clientController));
    }

    protected void setSystemInfo (String info) {
        systemInfo.setText(info);
    }
}
