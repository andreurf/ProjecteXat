package com.projecte.serveis;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.projecte.models.Usuari;
import org.bson.Document;

public class MongoDBConexio {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> userCollection;

    public MongoDBConexio() {
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
    
    public boolean iniciarSessio(String usuari, String contrasenya) {
    Document query = new Document("usuari", usuari).append("password", contrasenya);
    FindIterable<Document> result = userCollection.find(query);
    return result.first() != null;
}

    
}