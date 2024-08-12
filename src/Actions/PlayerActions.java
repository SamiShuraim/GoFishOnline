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
        // Send req
        outputStream.println(encryptMessage(new String[]{"2"}));

        // Print message
        try {
            String answer = inputStream.readLine();
            return getOnlinePlayersTabularForm(answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String viewOngoingGames() {
        return "true";
    }

    public String unregister() {
        // Send req
        outputStream.println(encryptMessage(new String[]{"4", name, socket.getInetAddress().getHostAddress()}));

        // Print message
        try {
            return inputStream.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private String getOnlinePlayersTabularForm(String answer) {
        String[] decryptedMessage = decryptMessage(answer);
        String res = "";

        res += String.format("%-15s %-15s", "Name", "Address") + "\n";
        for (int i = 0; i < decryptedMessage.length; i += 2) {
            res += String.format("%-15s %-15s", decryptedMessage[i], decryptedMessage[i + 1]) + "\n";
        }

        return res;
    }
}
