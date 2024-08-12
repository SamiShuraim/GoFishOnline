package Player;

import Actions.PlayerActions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Player {
    private static String name;
    private static Socket socket;
    private static PrintStream outputStream;
    private static BufferedReader inputStream;

    private static PlayerActions playerActions;

    public static void main(String[] args) throws IOException {
        initializeSocket();
        name = args[0];
        playerActions = new PlayerActions(name, socket, outputStream, inputStream);
        while (true) {
            printOptions();
            int option = getUsersOption();

            String answer = playerActions.doOption(option);
            System.out.println(answer);
        }
    }


    private static void initializeSocket() {
        try {
            socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 3728);
            outputStream = new PrintStream(socket.getOutputStream());
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return;
        } catch (UnknownHostException e) {
            System.out.println("Error: Manager IP address could not be resolved.");
            System.exit(1); // Terminate when there is an error
        } catch (IOException e) {
            System.out.println("Error: Socket could not be created.");
            System.exit(1); // Terminate when there is an error
        }
    }

    public static void printOptions() {
        System.out.println("  1. Register");
        System.out.println("  2. View Online Players");
        System.out.println("  3. View Ongoing Games");
        System.out.println("  4. Unregister");
        System.out.println("  5. Start game");
        System.out.println(" 99. Exit");
    }

    private static int getUsersOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your choice: ");
        int option = scanner.nextInt();

        for (int i : new int[]{1, 2, 3, 4, 5, 99})
            if (i == option)
                return option;

        System.out.println("Error: Invalid choice. Please type one of the numbers above.");
        return getUsersOption();
    }

    public static String getName() {
        return name;
    }

    public static Socket getSocket() {
        return socket;
    }

    public static PrintStream getOutputStream() {
        return outputStream;
    }

    public static BufferedReader getInputStream() {
        return inputStream;
    }
}