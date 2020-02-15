package io.serumdev.clansystem.listeners;

import io.serumdev.clansystem.BungeeClans;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 *
 * @author Tim Wöhrle
 * 
 * Copyright (c) 2011 - 2019 by SerumDev.io to present. All rights reserved. 
 */
public class LoginListener implements Listener {
    
    private final BungeeClans plugin;

    public LoginListener(BungeeClans plugin) {
        this.plugin = plugin;
        this.plugin.getProxy().getPluginManager().registerListener(this.plugin, this);
    }
    
    @EventHandler
    public void onLogin(LoginEvent event) {
        PendingConnection player = event.getConnection();
        
        this.plugin.getBackendManager().createUser(player);
    }

}
