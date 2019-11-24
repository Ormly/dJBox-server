package org.pineapple.core;

import java.util.UUID;

/**
 * This class represents a user of the Jukebox
 */
public class User
{
    private String userName;
    private String passwordHash;
    private Token token;

    public User(String userName, String passwordHash)
    {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.token = null;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setToken(String token)
    {
        if(token != null)
            this.token = new Token(UUID.fromString(token));
        else
            this.token = null;
    }

    public String getToken()
    {
        if(token != null)
            return this.token.getToken();
        else
            return null;

    }

}
