package Manager;

import java.net.ServerSocket;
import java.net.Socket;

public class Manager {
    public static void main(String[] args) {
        try {
//            int serverPortNumber = Integer.parseInt(args[0]);
            ServerSocket connectionSocket = new ServerSocket(3728);
            while (true) {
                Socket dataSocket = connectionSocket.accept(); // accepts each request sent to it
                ManagerThread thread = new ManagerThread(dataSocket);
                thread.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}