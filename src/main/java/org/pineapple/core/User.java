package org.pineapple.core;

import java.util.UUID;

/**
 * This class represents a user of the Jukebox.
 */
public class User
{
    private String userEmail;
    private String passwordHash;
    private Token token;

    /**
     * Generates a new user. By default the user is not authenticated.
     * @param userEmail
     * @param passwordHash
     */
    public User(String userEmail, String passwordHash)
    {
        this.userEmail = userEmail;
        this.passwordHash = passwordHash;
        this.token = null;
    }

    /**
     * Returns the user email which is also their username.
     * @return String userEmail
     */
    public String getUserName()
    {
        return userEmail;
    }

    /**
     * Returns the password of the user, hashed.
     * @return String password
     */
    public String getPasswordHash()
    {
        return passwordHash;
    }

    /**
     * Sets the user token to the passed value.
     */
    public void setToken(String token)
    {
        if(token != null)
            this.token = new Token(UUID.fromString(token));
        else
            this.token = null;
    }

    /**
     * Returns the token of the user.
     * @return String token
     */
    public String getToken()
    {
        if(token != null)
            return this.token.getToken();
        else
            return null;

    }

}
