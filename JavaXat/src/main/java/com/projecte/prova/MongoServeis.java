package com.projecte.prova;

import com.mongodb.client.*;
import com.mongodb.MongoClientURI;
import com.mongodb.client.model.Filters;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MongoServeis {

    private final com.mongodb.MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> missatgesCollection;
    private final MongoCollection<Document> usuarisCollection;

    public MongoServeis() {
        MongoClientURI uri = new MongoClientURI("mongodb://grup2:gos456@57.129.5.24:27017");
        mongoClient = new com.mongodb.MongoClient(uri);
        database = mongoClient.getDatabase("grup2");
        missatgesCollection = database.getCollection("missatges");
        usuarisCollection = database.getCollection("usuaris");
    }

    // Actualización del método desarMissatge
    public void desarMissatge(Missatge missatge) {
        Document document = new Document("usuari", missatge.getNomUsuari())
                .append("missatge", missatge.getMissatge())
                .append("dataHora", missatge.getDataHora())
                .append("grup", missatge.getGrup());
        missatgesCollection.insertOne(document);
    }

    // Mètode per recuperar els missatges d'un dia concret per a un grup determinat
    public List<Missatge> obtenirMissatgesPerGrup(String grup) {
        List<Missatge> missatgesGrup = new ArrayList<>();
        MongoCursor<Document> cursor = missatgesCollection.find(Filters.eq("grup", grup)).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String nomUsuari = doc.getString("usuari");
                String missatge = doc.getString("missatge");
                Date dataHora = doc.getDate("dataHora");
                missatgesGrup.add(new Missatge(nomUsuari, missatge, dataHora, grup));
            }
        } finally {
            cursor.close();
        }
        return missatgesGrup;
    }

    public List<Missatge> obtenirMissatgesPerUsuari(String nomUsuari) {
        List<Missatge> missatges = new ArrayList<>();
        FindIterable<Document> result = missatgesCollection.find(Filters.eq("usuari", nomUsuari));
        try (MongoCursor<Document> cursor = result.iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String missatge = doc.getString("missatge");
                Date dataHora = new Date();
                String grup = "Grupo predeterminado";
                missatges.add(new Missatge(nomUsuari, missatge, dataHora, grup));
            }
        }
        return missatges;
    }

    // Mètode per desar un usuari connectat a la base de dades
    public void desarUsuari(Usuari usuari) {
        Document document = new Document("usuari", usuari.getNomUsuari())
                .append("contrasenya", usuari.getContrasenya())
                .append("dataHora", usuari.getHoraConnexio())
                .append("grup", usuari.getGrup());
        usuarisCollection.insertOne(document);
    }

    // Mètode per recuperar tots els usuaris connectats
    public List<String> obtenirNomsUsuarisConnectats() {
        List<String> nomsUsuarisConnectats = new ArrayList<>();
        MongoCursor<Document> cursor = usuarisCollection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                String usuari = document.getString("usuari");
                nomsUsuarisConnectats.add(usuari);
            }
        } finally {
            cursor.close();
        }
        return nomsUsuarisConnectats;
    }

    public List<String> obtindreNomsUsuaris(String nomUsuari) {
        List<String> nomUsuaris = new ArrayList<>();
        FindIterable<Document> result = usuarisCollection.find();
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

    public boolean validarUsuari(String usuari) {
        Document query = new Document("usuari", usuari);
        FindIterable<Document> result = usuarisCollection.find(query);
        return result.first() != null;
    }

    public boolean iniciarSecio(String usuari, String contrasenya) {
        Document query = new Document("usuari", usuari).append("contrasenya", contrasenya);
        FindIterable<Document> result = usuarisCollection.find(query);
        return result.first() != null;
    }

}
