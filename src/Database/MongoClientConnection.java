package Database;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class MongoClientConnection {
    private static Properties props;
    private MongoDatabase database;
    private MongoCollection playersTable;
    private MongoCollection gamesTable;

    public MongoClientConnection() {
        loadEnv();
        String connectionString = String.format("mongodb+srv://%s:%s@cluster0.ube5t.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0", props.get("user"), props.get("password"));
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        // Create a new client and connect to the server
        MongoClient mongoClient = MongoClients.create(settings);
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

    private static void loadEnv() {
        props = new Properties();
        Path dotenvPath = Paths.get(".env");
        try (InputStream inputStream = Files.newInputStream(dotenvPath)) {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
