import javax.swing.*;
import java.awt.*;

public class RegistrationWindow extends JFrame {
    private JTextField LoginField;
    private JPasswordField passwordField;
    private JTextField nickField;
    private JButton registrationButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel nickLabel;
    private JLabel systemInfo;
    private JPanel registPanel;

    public RegistrationWindow() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 700, 800);
        setContentPane(registPanel);
        setVisible(true);
    }

}
