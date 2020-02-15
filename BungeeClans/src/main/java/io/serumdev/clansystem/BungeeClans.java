package io.serumdev.clansystem;

import io.serumdev.clansystem.listeners.LoginListener;
import io.serumdev.clansystem.managers.BackendManager;
import io.serumdev.clansystem.managers.MongoManager;
import net.md_5.bungee.api.plugin.Plugin;

/**
 *
 * @author Tim Wöhrle
 *
 * Copyright (c) 2011 - 2019 by SerumDev.io to present. All rights reserved.
 */
public class BungeeClans extends Plugin {

    private BackendManager backendManager;
    
    private MongoManager mongoManager;
    
    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {
        this.mongoManager.getClient().close();
    }

    private void init() {
        this.mongoManager = new MongoManager("127.0.0.1", 27017);
        this.mongoManager.connect();
        
        this.backendManager = new BackendManager(this);
        
        new LoginListener(this);
    }

    public BackendManager getBackendManager() {
        return backendManager;
    }
    
    public MongoManager getMongoManager() {
        return mongoManager;
    }
}
