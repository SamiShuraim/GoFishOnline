package Actions;

import Objects.PlayerObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ManagerActions extends Actions {
    private Socket socket;
    private String[] message;
    private MongoCollection playersTable;
    private MongoCollection gamesTable;

    public ManagerActions(Socket socket, PrintStream outputStream, BufferedReader inputStream, MongoCollection playersTable, MongoCollection gamesTable) {
        super(null, socket, outputStream, inputStream);
        this.playersTable = playersTable;
        this.gamesTable = gamesTable;
    }

    @Override
    public String register() { // Example message: 1=Sami=192.168.0.1
        try {
            PlayerObject playerObject = new PlayerObject(message[1], InetAddress.getByName(message[2]));
//            playersTable.find(new Document(playerObject.toMap()));

            playersTable.insertOne(new Document(playerObject.toMap()));
            return "Player registered Successfully.";
        } catch (UnknownHostException e) {
            System.out.println("Error: Could not create player object. Ip address unknown.");
            return "Error: Player could not be registered.";
        }
    }

    @Override
    public String viewOnlinePlayers() {
        ArrayList<String> res = new ArrayList<>();

        Bson projection = Projections.fields(Projections.excludeId());
        playersTable.find().projection(projection).forEach((o) -> {
            Document p = (Document) o;
            res.add((String) p.get("name"));
            res.add((String) p.get("address"));
        });

        return encryptMessage(res);
    }

    @Override
    public String viewOngoingGames() {
        return null;
    }

    @Override
    public String unregister() {
        return null;
    }

    @Override
    public String startGame() {
        return null;
    }

    @Override
    public String exit() {
        return null;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
