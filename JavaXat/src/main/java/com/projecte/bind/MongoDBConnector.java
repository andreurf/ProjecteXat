package com.projecte.bind;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBConnector {

    private MongoClient clientMongo;
    private MongoDatabase baseDeDades;

    public void connectar(String ip, String nomBaseDeDades, String usuari, String contrasenya) {
        String cadenaConnexio = "mongodb://" + usuari + ":" + contrasenya + "@" + ip + ":27017";
        clientMongo = MongoClients.create(cadenaConnexio);
        baseDeDades = clientMongo.getDatabase(nomBaseDeDades);
    }

    public MongoIterable<String> obtenirColleccions() {
        return baseDeDades.listCollectionNames();
    }

    public List<Document> carregarDadesColleccio(String nomColleccio) {
        MongoCollection<Document> colleccio = baseDeDades.getCollection(nomColleccio);
        MongoCursor<Document> cursor = colleccio.find().iterator();

        List<Document> documents = new ArrayList<>();
        while (cursor.hasNext()) {
            documents.add(cursor.next());
        }
        cursor.close();

        return documents;
    }

    public void actualitzarDocument(String nomColleccio, Document documentOriginal, Document documentActualitzat) {
        MongoCollection<Document> colleccio = baseDeDades.getCollection(nomColleccio);
        colleccio.replaceOne(documentOriginal, documentActualitzat);
    }
}
