package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    //    private ClientController clientController;

    public RegistrationWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setContentPane(registPanel);
        registrationButton.addActionListener(e -> sendRegistTokens());
        setVisible(true);
    }

    private void sendRegistTokens (){
        String registTokens = "/regist " + loginField.getText() + " " + Arrays.hashCode(passwordField.getPassword()) + " " + nickField.getText();
        // send Tokens by ClientController
    }

}
