import javax.swing.*;
import java.awt.*;

public class AuthWindow extends JFrame {
    private JTextField login = new JTextField("Login");
    private JPasswordField password = new JPasswordField("Password");
    private JButton auth = new JButton("Войти");
    private JButton regist = new JButton("Регистрация");

    public AuthWindow() {
        setTitle("Auth");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 250, 200);


        setLayout(new BorderLayout());
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setPreferredSize(new Dimension(250,100));
        textPanel.add(login);
        textPanel.add(password);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
//        buttonsPanel.setPreferredSize(new Dimension(250,50));
//        auth.setPreferredSize(new Dimension(125, 50));
//        regist.setPreferredSize(new Dimension(125, 50));
        buttonsPanel.add(auth);
        buttonsPanel.add(regist);

        add(textPanel, BorderLayout.PAGE_START);
        add(buttonsPanel, BorderLayout.PAGE_END);
        setVisible(true);
    }

}
