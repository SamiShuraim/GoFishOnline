package Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Player {
    Socket socket;
    PrintStream outputStream;
    BufferedReader inputStream;
    
    public static void main(String[] args) {
        InitializeSocket();
    }

    private static void InitializeSocket() {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 3728);
            PrintStream outputStream = new PrintStream(socket.getOutputStream());
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return;
        } catch (UnknownHostException e) {
            System.out.println("Error: Manager IP address could not be resolved.");
        } catch (IOException e) {
            System.out.println("Error: Socket could not be created.");
        } finally {
            System.exit(1); // Terminate when there is an error
        }
    }
}