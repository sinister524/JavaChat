package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

public class RegistrationWindow extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField nickField;
    private JButton registrationButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel nickLabel;
    private JLabel systemInfo;
    private JPanel registPanel;
        private ClientController clientController;

    public RegistrationWindow(ClientController clientController) {
        this.clientController = clientController;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setContentPane(registPanel);
        registrationButton.addActionListener(e -> sendRegistTokens());
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                clientController.sendMessage("/end");
                System.exit(0);
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

    private void sendRegistTokens (){
        String registTokens = "/regist " + loginField.getText() + " " + Arrays.hashCode(passwordField.getPassword()) + " " + nickField.getText();
        this.clientController.sendMessage(registTokens);
    }

    public void setSystemInfo (String info) {
        systemInfo.setText(info);
    }
}
