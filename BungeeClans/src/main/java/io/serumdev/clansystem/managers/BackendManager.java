package io.serumdev.clansystem.managers;

import com.mongodb.client.model.Filters;
import io.serumdev.clansystem.BungeeClans;
import io.serumdev.clansystem.misc.entites.User;
import java.util.HashMap;
import net.md_5.bungee.api.connection.PendingConnection;
import org.bson.Document;

/**
 *
 * @author Tim Wöhrle
 *
 * Copyright (c) 2011 - 2019 by SerumDev.io to present. All rights reserved.
 */
public class BackendManager {
    
    private final BungeeClans plugin;
    
    private final HashMap<String, User> users;
    
    public BackendManager(BungeeClans plugin) {
        this.plugin = plugin;
        this.users = new HashMap<>();
    }
    
    public void createUser(PendingConnection player) {
        if (!existUser(player.getUniqueId().toString())) {
            User user = new User();
            user.setUuid(player.getUniqueId().toString());
            user.setUserName(player.getName());
            user.setClanCode("");
            
            Document createdUser = new Document();
            createdUser.append("uuid", user.getUuid()).append("userName", user.getUserName())
                    .append("clanCode", user.getClanCode());
            
            this.plugin.getMongoManager().getPlayers().insertOne(createdUser);
            
            this.users.put(user.getUuid(), user);
        } else {
            Document foundPlayer = this.plugin.getMongoManager().getPlayers().find(Filters.eq("uuid", player.getUniqueId().toString())).first();
            
            User user = new User();
            user.setUuid(foundPlayer.getString("uuid"));
            user.setUserName(foundPlayer.getString("userName"));
            user.setClanCode(foundPlayer.getString("clanCode"));
            
            this.users.put(user.getUuid(), user);
        }
    }
    
    public User getUser(String uuid) {
        if (this.users.get(uuid) != null)
            return this.users.get(uuid);
        
        Document foundPlayer = this.plugin.getMongoManager().getPlayers().find(Filters.eq("uuid", uuid)).first();
        
        if (foundPlayer != null)
            return new User(foundPlayer.getString("uuid"), foundPlayer.getString("userName"),
                    foundPlayer.getString("clanCode"));
        return null;
    }
    
    private boolean existUser(String uuid) {
        return this.plugin.getMongoManager().getPlayers().find(Filters.eq("uuid", uuid)).first() != null;
    }
}
