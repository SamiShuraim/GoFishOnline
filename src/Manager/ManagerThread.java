package Manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ManagerThread extends Manager implements Runnable{
    Socket dataSocket;
    PrintStream outputStream;
    BufferedReader inputStream;

    public ManagerThread(Socket dataSocket) {
        try {
            this.dataSocket = dataSocket;
            outputStream = new PrintStream(dataSocket.getOutputStream());
            inputStream = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error: Could not create streams or socket is not connected.");
            System.exit(1); // Terminate when there is an error.
        }
    }
    @Override
    public void run() {
        try {
            String message = inputStream.readLine();
            System.out.println(message);
            outputStream.println("Received");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
