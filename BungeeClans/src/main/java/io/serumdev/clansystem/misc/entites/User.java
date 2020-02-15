package io.serumdev.clansystem.misc.entites;

/**
 *
 * @author Tim Wöhrle
 * 
 * Copyright (c) 2011 - 2019 by SerumDev.io to present. All rights reserved. 
 */
public class User {

    private String uuid;
    private String userName;
    private String clanCode;

    public User() {
    }

    public User(String uuid, String userName, String clanCode) {
        this.uuid = uuid;
        this.userName = userName;
        this.clanCode = clanCode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClanCode() {
        return clanCode;
    }

    public void setClanCode(String clanCode) {
        this.clanCode = clanCode;
    }
    
    
}
