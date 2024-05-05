package com.projecte.service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.projecte.models.Usuari;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class ServidorMDB {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;

    public ServidorMDB() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("projecte");
        userCollection = database.getCollection("usuaris");
    }

    public void insertUser(Usuari user) {
        Document userDoc = new Document("usuari", user.getUsuari())
                .append("password", user.getPassword());
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

}
