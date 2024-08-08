package Manager;

import java.net.Socket;

public class ManagerThread extends Manager implements Runnable{
    Socket dataSocket;

    public ManagerThread(Socket dataSocket) {
        this.dataSocket = dataSocket;
    }
    @Override
    public void run() {
        System.out.println("Hello");
        System.out.println(dataSocket.getInetAddress());
    }
}
