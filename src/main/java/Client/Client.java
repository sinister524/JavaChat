package Client;

import java.io.*;
import java.net.Socket;

public class Client {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Socket socket = new Socket("localhost", 5000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (socket.isConnected()) {
                        String str = in.readUTF();

                        System.out.println("Сервер: " + str);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }).start();
            while (socket.isConnected()) {
                String str = reader.readLine();
                if (str.equals("/end")) {
                    socket.close();
                    break;
                }
                out.writeUTF(str);
            }
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
