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
        while (!socket.isClosed()) {
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
        while (socket.isConnected()){
            try {
                String inStr = inputStream.readUTF();
                if (inStr.equalsIgnoreCase("/end")) {
                    close();
                    break;
                }
                if (inStr.startsWith("/auth")) {
                    server.logger.info("Client " + socket.toString() + " try to authorization");
                    String[] tokens = inStr.split(" ");
                    String nick = AuthService.authorization(tokens[1], tokens[2]);
                    if (nick != null){
                        sendMessage("/authOK");
                        server.logger.info("Client " + socket.toString() + " authorization success");
                        this.nick = nick;
                        server.subscribe(this);
                        break;
                    }else {
                        sendMessage("/authNotOK");
                        server.logger.info("Client " + socket.toString() + " authorization failed");
                    }
                }
                if (inStr.startsWith("/regist")){
                    server.logger.info("Client " + socket.toString() + " try to registration");
                    String[] tokens = inStr.split(" ");
                    boolean registIsOK = AuthService.registration(tokens[1], tokens[2], tokens[3]);
                    if (registIsOK){
                        sendMessage("/registOK");
                        server.logger.info("Client " + socket.toString() + " registration success");
                        this.nick = tokens[3];
                        server.subscribe(this);
                        break;
                    } else {
                        sendMessage("/registNotOK");
                        server.logger.info("Client " + socket.toString() + " registration failed");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                server.logger.error("Error message", e);
            }
        }
    }

    protected void sendMessage(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
            server.logger.error("Error message", e);
        }
    }

    private void close (){
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
            server.logger.info("Client " + socket.toString() + " disconnected");
        } catch (IOException e) {
            e.printStackTrace();
            server.logger.error("Error message", e);
        }
        server.unsubscribe(this);
    }
}
