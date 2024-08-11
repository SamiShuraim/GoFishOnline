package Actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class PlayerActions extends Actions {
    public PlayerActions(String name, Socket socket, PrintStream outputStream, BufferedReader inputStream) {
        super(name, socket, outputStream, inputStream);
    }

    public String register() {
        // Send req
        outputStream.println(encryptMessage(new String[]{"1", name, socket.getInetAddress().getHostAddress()}));

        // Print message
        try {
            return inputStream.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String viewOnlinePlayers() {
        return "true";
    }

    public String viewOngoingGames() {
        return "true";
    }

    public String unregister() {
        return "true";
    }

    public String startGame() {
        return "true";
    }

    public String exit() {
        return "true";
    }

    public String encryptMessage(String[] rawMessage) {
        String res = "";

        for (String s : rawMessage)
            res += s + "=";

        res = res.substring(0, res.lastIndexOf("="));
        return res;
    }
}
