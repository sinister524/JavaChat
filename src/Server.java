import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Server {

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Сервер запущен, ждёт подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outPutStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String strFromClient = dataInputStream.readUTF();
                if (strFromClient.equalsIgnoreCase("/end")) {
                    break;
                }
                System.out.println("log: " + LocalDate.now() + " " + strFromClient);
                outPutStream.writeUTF("echo: " + strFromClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




