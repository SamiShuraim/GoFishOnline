package Actions;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

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

    public abstract String exit();

    public String encryptMessage(String[] rawMessage) {
        String res = "";

        for (String s : rawMessage)
            res += s + "=";

        res = res.substring(0, res.lastIndexOf("="));
        return res;
    }

    public String[] decryptMessage(String message) {
        return message.split("=");
    }
}
