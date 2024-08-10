package Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Options {
    private static String name = Player.getName();
    private static Socket socket = Player.getSocket();
    private static PrintStream outputStream = Player.getOutputStream();
    private static BufferedReader inputStream = Player.getInputStream();

    public static void doOption(int option) {
        switch (option) {
            case 1:
                register();
                break;
            case 2:
                viewOnlinePlayers();
                break;
            case 3:
                viewOngoingGames();
                break;
            case 4:
                unregister();
                break;
            case 5:
                startGame();
                break;
            case 99:
                exit();
                break;
        }
    }

    public static boolean register() {
        // Send req
        outputStream.println(encryptMessage(new String[]{"1", name}));

        // Print message
        try {
            String res = inputStream.readLine();
            return res == "0";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean viewOnlinePlayers() {
        return true;
    }

    public static boolean viewOngoingGames() {
        return true;
    }

    public static boolean unregister() {
        return true;
    }

    public static boolean startGame() {
        return true;
    }

    public static boolean exit() {
        return true;
    }

    public static String encryptMessage(String[] rawMessage) {
        String res = "";

        for (String s : rawMessage)
            res += s + "=";

        res = res.substring(0, res.lastIndexOf("="));
        return res;
    }
}
