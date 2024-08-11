package Manager;

import Database.MongoClientConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.net.ServerSocket;
import java.net.Socket;

public class Manager {
    private static MongoDatabase database;
    private static MongoCollection playersTable;
    private static MongoCollection gamesTable;
    public static void main(String[] args) {
        MongoClientConnection databaseConnection = new MongoClientConnection();
        try {
            database = databaseConnection.getDatabase();
            playersTable = databaseConnection.getPlayersTable();
            gamesTable = databaseConnection.getGamesTable();
//            int serverPortNumber = Integer.parseInt(args[0]);
            ServerSocket connectionSocket = new ServerSocket(3728);
            while (true) {
                Socket dataSocket = connectionSocket.accept(); // accepts each request sent to it
                ManagerThread thread = new ManagerThread(dataSocket);
                thread.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static MongoCollection getPlayersTable() {
        return playersTable;
    }

    public static MongoCollection getGamesTable() {
        return gamesTable;
    }
}