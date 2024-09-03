package Actions;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public abstract class Actions {
    String name;
    Socket socket;
    PrintStream outputStream;
    BufferedReader inputStream;

    public Actions(String name, Socket socket, PrintStream outputStream, BufferedReader inputStream) {
        this.name = name;
        this.socket = socket;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    public String doOption(int option) {
        return switch (option) {
            case 1 -> register();
            case 2 -> viewOnlinePlayers();
            case 3 -> viewOngoingGames();
            case 4 -> unregister();
            case 5 -> startGame();
            case 99 -> exit();
            default -> "Error: Something went wrong";
        };
    }

    public abstract String register();

    public abstract String viewOnlinePlayers();

    public abstract String viewOngoingGames();

    public abstract String unregister();

    public abstract String startGame();

    public String exit() {
        return "";
    }

    public String encryptMessage(String[] rawMessage) {
        StringBuilder res = new StringBuilder();

        for (String s : rawMessage)
            res.append(s).append("=");

        res = new StringBuilder(res.substring(0, res.lastIndexOf("=")));
        return res.toString();
    }

    public String encryptMessage(ArrayList<String> rawMessage) {
        StringBuilder res = new StringBuilder();

        for (String s : rawMessage) {
            res.append(s).append("=");
        }

        res = new StringBuilder(res.substring(0, res.lastIndexOf("=")));
        return res.toString();
    }

    public String[] decryptMessage(String message) {
        return message.split("=");
    }
}
