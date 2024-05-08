package com.projecte.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.projecte.models.Usuari;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class ServidorMDB {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;

    public ServidorMDB() {
        MongoClientURI uri = new MongoClientURI("mongodb://grup2:gos456@57.129.5.24:27017");
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDatabase("grup2");
        userCollection = database.getCollection("usuaris");
    }

    public void insertUser(Usuari user) {
        String passwordHash = hashPassword(user.getPassword());
        Document userDoc = new Document("usuari", user.getUsuari())
                .append("password", passwordHash);
        userCollection.insertOne(userDoc);
    }

    public boolean validarUsuari(String usuari) {
        Document query = new Document("usuari", usuari);
        FindIterable<Document> result = userCollection.find(query);
        return result.first() != null;
    }

    public boolean iniciarSecio(String usuari, String contrasenya) {
        Document query = new Document("usuari", usuari).append("password", contrasenya);
        FindIterable<Document> result = userCollection.find(query);
        return result.first() != null;
    }

    public List<String> obtindreNomsUsuaris(String nomUsuari) {
        List<String> nomUsuaris = new ArrayList<>();
        FindIterable<Document> result = userCollection.find();
        try (MongoCursor<Document> cursor = result.iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String nom = doc.getString("usuari");
                // Excloure el nom del usuari actual
                if (!nom.equals(nomUsuari)) {
                    nomUsuaris.add(nom);
                }
            }
        }
        return nomUsuaris;
    }

    // Método para cifrar la contraseña usando SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = password.getBytes();
            byte[] hashBytes = digest.digest(passwordBytes);
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
