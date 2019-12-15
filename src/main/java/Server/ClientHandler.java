package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private String nick;

    protected ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                authorization();
                read();
                close();
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getNick() {
        return nick;
    }

    private void read() {
        while (true) {
            try {
                String inString = inputStream.readUTF();
                if (inString.startsWith("/")){
                    if (inString.equalsIgnoreCase("/end")) {
                        break;
                    }
                    if (inString.startsWith("/w ")) {
                        String[] tokens = inString.split(" ", 3);
                        server.sendPersonalMsg(this, tokens[1], tokens[2]);
                    }
                } else {
                    server.broadcast(nick +": " + inString);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void authorization() {
        while (true){
            try {
                String inStr = inputStream.readUTF();
                if (inStr.startsWith("/auth")) {
                    String[] tokens = inStr.split(" ");
                    String nick = AuthService.authorization(tokens[1], tokens[2]);
                    if (nick != null){
                        sendMessage("/authOK");
                        this.nick = nick;
                        server.subscribe(this);
                        break;
                    }else {
                        sendMessage("/authNotOK");
                    }
                }
                if (inStr.startsWith("/regist")){
                    String[] tokens = inStr.split(" ");
                    boolean registIsOK = AuthService.registration(tokens[1], tokens[2], tokens[3]);
                    if (registIsOK){
                        sendMessage("/registOK");
                        this.nick = tokens[3];
                        server.subscribe(this);
                        break;
                    } else {
                        sendMessage("/registNotOK");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void sendMessage(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close (){
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.unsubscribe(this);
    }
}
