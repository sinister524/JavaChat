package Client;

import java.io.*;
import java.net.Socket;

public class ClientController {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 5000;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String inMessage;
    private AuthWindow authWindow;
    private RegistrationWindow registrationWindow;
    private ChatWindow chatWindow;


    public ClientController() {
        try {
            socket = new Socket("localhost", 5000);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (socket.isConnected()) {
                        inMessage = in.readUTF();
                        if (inMessage.equals("/authOK")){
                            authWindow.setVisible(false);
                            chatWindow = new ChatWindow(this);
                        } else if (inMessage.equals("/authNotOK")){
                            authWindow.setSystemInfo("Неверный логин/пароль");
                        } else if (inMessage.equals("/registOK")){
                            registrationWindow.setVisible(false);
                            chatWindow = new ChatWindow(this);
                        } else if (inMessage.equals("/registNotOK")){
                            registrationWindow.setSystemInfo("Логин/ник уже занят");
                        } else if (inMessage.startsWith("/clientlist")){
                            chatWindow.createClientList(inMessage);
                        } else {
                            chatWindow.acceptMessage(inMessage);
                        }
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }).start();
            authWindow = new AuthWindow(this);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void sendMessage (String message){
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRegistrationWindow(RegistrationWindow registrationWindow) {
        this.registrationWindow = registrationWindow;
    }
}
