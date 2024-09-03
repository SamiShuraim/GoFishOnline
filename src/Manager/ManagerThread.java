package Manager;

import Actions.ManagerActions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ManagerThread extends Manager implements Runnable {
    ManagerActions managerActions;
    Socket dataSocket;
    PrintStream outputStream;
    BufferedReader inputStream;

    public ManagerThread(Socket dataSocket) {
        try {
            this.dataSocket = dataSocket;
            outputStream = new PrintStream(dataSocket.getOutputStream());
            inputStream = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
            managerActions = new ManagerActions(dataSocket, outputStream, inputStream, getPlayersTable(), getGamesTable());
        } catch (IOException e) {
            System.out.println("Error: Could not create streams or socket is not connected.");
            System.exit(1); // Terminate when there is an error.
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String[] decryptedMessage = managerActions.decryptMessage(inputStream.readLine());

                managerActions.setMessage(decryptedMessage);
                String answer = managerActions.doOption(Integer.parseInt(decryptedMessage[0]));

                System.out.println(answer);
                outputStream.println(answer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
