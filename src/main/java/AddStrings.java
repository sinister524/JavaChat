
import javax.swing.text.JTextComponent;

public class AddStrings {
    public static void sendText(JTextComponent nickname, JTextComponent input, JTextComponent output) {
        if (!input.getText().isEmpty()){
            output.setText(output.getText() + nickname.getText() + ": " + input.getText() + "\n");
            input.setText(null);
        }
    }
}
