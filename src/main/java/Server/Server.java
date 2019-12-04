package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    private List<ClientHandler> clientsConnection = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            AuthService.connect();
            System.out.println("Сервер запущен, ждёт подключения...");
            while (!serverSocket.isClosed()){
                socket = serverSocket.accept();
                System.out.println("Клиент подключился");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void broadcast (String message){
        for (ClientHandler clientHandler: clientsConnection) {
            clientHandler.sendMessage(message);

        }
    }
    public void subscribe(ClientHandler clientHandler){
        clientsConnection.add(clientHandler);
        broadcast("System: " + clientHandler.getNick() + " подключился к чату");
        broadcastClientList();
    }
    public void unsubscribe (ClientHandler clientHandler){
        clientsConnection.remove(clientHandler);
        broadcast("System: " + clientHandler.getNick() + " покинул чат");
        broadcastClientList();
    }

    public void sendPersonalMsg(ClientHandler from, String nickTo, String message) {
        for (ClientHandler clientHandler : clientsConnection) {
            if (clientHandler.getNick().equalsIgnoreCase(nickTo)) {
                clientHandler.sendMessage("FROM: " + from.getNick() + " SEND: " + message);
                from.sendMessage("TO:  " + nickTo + " SEND: " + message);
                return;
            }
        }
        from.sendMessage("Клиент с ником: " + nickTo + " не найден в чате");
    }
    private void broadcastClientList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientlist ");
        for (ClientHandler clientHandler : clientsConnection) {
            sb.append(clientHandler.getNick()).append(" ");
        }
        String out = sb.toString();
        for (ClientHandler clientHandler : clientsConnection) {
            clientHandler.sendMessage(out);
        }
    }
}




