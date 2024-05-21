package com.projecte.bind;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ConnexioMongo {

    private MongoClient clientMongo;
    private MongoDatabase baseDeDades;

    public boolean connectar(String ip, String nomBaseDeDades, String usuari, String contrasenya) {
        if (!ip.isEmpty() && !nomBaseDeDades.isEmpty() && !usuari.isEmpty() && !contrasenya.isEmpty()) {
            try {
                String cadenaConnexio = "mongodb://" + usuari + ":" + contrasenya + "@" + ip + ":27017";
                clientMongo = MongoClients.create(cadenaConnexio);
                baseDeDades = clientMongo.getDatabase(nomBaseDeDades);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error d'autenticació: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Si us plau, emplena tots els camps: IP del servidor, nom de la base de dades, usuari i contrasenya.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public List<String> obtenirNomsColleccions() {
        List<String> nomsColleccions = new ArrayList<>();
        if (baseDeDades != null) {
            MongoIterable<String> colleccions = baseDeDades.listCollectionNames();
            for (String colleccio : colleccions) {
                nomsColleccions.add(colleccio);
            }
        }
        return nomsColleccions;
    }

    public List<Document> carregarColleccio(String nomColleccio) {
        List<Document> documents = new ArrayList<>();
        if (baseDeDades != null && nomColleccio != null) {
            MongoCollection<Document> colleccio = baseDeDades.getCollection(nomColleccio);
            MongoCursor<Document> cursor = colleccio.find().iterator();

            while (cursor.hasNext()) {
                documents.add(cursor.next());
            }

            cursor.close();
        }
        return documents;
    }

    public void actualitzarDocument(String nomColleccio, Document documentOriginal, Document documentActualitzat) {
        if (baseDeDades != null && nomColleccio != null) {
            MongoCollection<Document> colleccio = baseDeDades.getCollection(nomColleccio);
            colleccio.replaceOne(documentOriginal, documentActualitzat);
        }
    }

    public void eliminarDocumentPerId(String nomColleccio, Object id) {
        if (baseDeDades != null && nomColleccio != null) {
            MongoCollection<Document> colleccio = baseDeDades.getCollection(nomColleccio);
            colleccio.deleteOne(new Document("_id", id));
        }
    }

}
