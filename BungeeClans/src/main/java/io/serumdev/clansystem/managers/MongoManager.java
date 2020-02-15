package io.serumdev.clansystem.managers;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;
import org.bson.Document;

/**
 *
 * @author Tim Wöhrle
 *
 * Copyright (c) 2011 - 2019 by SerumDev.io to present. All rights reserved.
 */
public class MongoManager {

    private final String hostName;
    private final int port;

    private MongoClient client;
    private MongoDatabase database;

    private MongoCollection<Document> players;
    private MongoCollection<Document> clans;

    public MongoManager(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void connect() {
        this.client = new MongoClient(this.hostName, this.port);
        this.database = this.client.getDatabase("youtube-dev");

        this.players = this.database.getCollection("players");
        this.clans = this.database.getCollection("clans");

        System.out.println("[MongoDB] Connection established!");
    }

    public void connect(String userName, String password) {
        MongoCredential credential = MongoCredential.createCredential(userName, "admin", password.toCharArray());
        this.client = new MongoClient(new ServerAddress(this.hostName, this.port), Arrays.asList(credential));
        this.database = this.client.getDatabase("youtube-dev");

        this.players = this.database.getCollection("players");
        this.clans = this.database.getCollection("clans");

        System.out.println("[MongoDB] Connection established!");
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
    
    public MongoCollection<Document> getPlayers() {
        return players;
    }

    public MongoCollection<Document> getClans() {
        return clans;
    }
}
