package org.pineapple.core;

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
        this.token.setToken(token);
    }

    public String getToken()
    {
        return this.token.getToken();
    }

}
