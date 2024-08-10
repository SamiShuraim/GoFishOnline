package Manager;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoClientConnection {
    private MongoDatabase database;
    private MongoCollection playersTable;
    private MongoCollection gamesTable;

    public MongoClientConnection() {
        String connectionString = "mongodb+srv://Sami:Sami2002w@cluster0.ube5t.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                database = mongoClient.getDatabase("GoFishOnline");
                playersTable = database.getCollection("Players");
                gamesTable = database.getCollection("Games");
                database.runCommand(new Document("ping", 1));
                System.out.println("Database connected.");
            } catch (MongoException e) {
                System.out.println("Error: Cannot connect to database.");
                System.exit(2);
            }
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection getPlayersTable() {
        return playersTable;
    }

    public MongoCollection getGamesTable() {
        return gamesTable;
    }
}
