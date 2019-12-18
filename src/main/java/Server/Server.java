package Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    private List<ClientHandler> clientsConnection = new CopyOnWriteArrayList<>();
    protected final Logger logger = LogManager.getLogger();

    protected Server () {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            AuthService.connect();
            System.out.println("Сервер запущен, ждёт подключения...");
            logger.info("Server is started");
            while (!serverSocket.isClosed()){
                socket = serverSocket.accept();
                System.out.println("Клиент подключился");
                logger.info("Connect unknown client " + socket.toString());
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error message", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.error("Error message", e);
            }
            AuthService.disconnect();
        }
    }

    protected void broadcast (String message){
        for (ClientHandler clientHandler: clientsConnection) {
            clientHandler.sendMessage(message);
            logger.info("Broadcast from " + message);
        }
    }
    protected void subscribe(ClientHandler clientHandler){
        clientsConnection.add(clientHandler);
        broadcast("System: " + clientHandler.getNick() + " подключился к чату");
        broadcastClientList();
    }
    protected void unsubscribe (ClientHandler clientHandler){
        clientsConnection.remove(clientHandler);
        broadcast("System: " + clientHandler.getNick() + " покинул чат");
        broadcastClientList();
    }

    protected void sendPersonalMsg(ClientHandler from, String nickTo, String message) {
        for (ClientHandler clientHandler : clientsConnection) {
            if (clientHandler.getNick().equalsIgnoreCase(nickTo)) {
                clientHandler.sendMessage("FROM: " + from.getNick() + " SEND: " + message);
                from.sendMessage("TO:  " + nickTo + " SEND: " + message);
                logger.info("Personal message FROM " + from.getNick() + " TO " + nickTo + " SEND: " + message);
                return;
            }
        }
        from.sendMessage("Клиент с ником: " + nickTo + " не найден в чате");
        logger.warn("Personal message from " + from.getNick() + " to " + nickTo + " not sent; client not found");
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
        logger.info("Broadcast client list: " + sb);
    }

}




