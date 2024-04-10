package com.projecte.service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.projecte.models.Usuari;
import org.bson.Document;

public class MongoDBConexio {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> userCollection;

    public MongoDBConexio() {
        // Establece la conexión con MongoDB
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("projecte");
        userCollection = database.getCollection("usuaris");
    }

    // Métodos para interactuar con la base de datos
    public void insertUser(Usuari user) {
        Document userDoc = new Document("usuari", user.getUsuari())
                            .append("email", user.getEmail())
                            .append("password", user.getPassword());
        userCollection.insertOne(userDoc);
    }

}
