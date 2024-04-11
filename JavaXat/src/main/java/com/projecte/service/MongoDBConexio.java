package com.projecte.service;

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
        // Establece la conexión con MongoDB
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("projecte");
        userCollection = database.getCollection("usuaris");
    }

    // Método para insertar un usuario en la base de datos
    public void insertUser(Usuari user) {
        Document userDoc = new Document("usuari", user.getUsuari())
                            .append("email", user.getEmail())
                            .append("password", user.getPassword());
        userCollection.insertOne(userDoc);
    }

    // Método para verificar si un nombre de usuario ya existe en la base de datos
    public boolean validarUsuari(String usuari) {
        Document query = new Document("usuari", usuari);
        FindIterable<Document> result = userCollection.find(query);
        return result.first() != null;
    }

    // Método para verificar si un correo electrónico ya existe en la base de datos
    public boolean validarEmail(String email) {
        Document query = new Document("email", email);
        FindIterable<Document> result = userCollection.find(query);
        return result.first() != null;
    }
    
    public boolean iniciarSecio(String usuari, String contrasenya) {
    Document query = new Document("usuari", usuari).append("password", contrasenya);
    FindIterable<Document> result = userCollection.find(query);
    return result.first() != null;
}

    
}
