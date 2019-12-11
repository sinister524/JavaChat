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

                        } else if (inMessage.equals("/authNitOK")){

                        } else if (inMessage.equals("/registOK")){

                        } else if (inMessage.equals("/registNotOK")){

                        } else if (inMessage.startsWith("/clientlist")){

                        } else {

                        }
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }).start();
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

}
