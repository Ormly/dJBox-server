package org.pineapple.core;

/**
 * This class represents a user of the Jukebox
 */
public class User
{
    private String userName;
    private String passwordHash;
    private Token token;
    private long userID;

    public User(String userName, String passwordHash, long userID)
    {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.token = null;
        this.userID = userID;
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

    public long getUserID()
    {
        return userID;
    }
}
