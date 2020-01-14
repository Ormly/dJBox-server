package org.pineapple.core;

import java.util.UUID;

/**
 * This class represents a token of the user.
 */
public class Token
{
    private UUID token;

    /**
     * Generates a new token.
     * @param token
     */
    public Token(UUID token)
    {
        this.token = token;
    }

    /**
     * Returns the token of the user.
     * @return String token
     */
    public String getToken()
    {
        return token.toString();
    }

    /**
     * Sets the token of the user to the passed string.
     * @param token
     */
    public void setToken(String token)
    {
        this.token = UUID.fromString(token);
    }

    /**
     * Converts the UUID generated value to a string token.
     * @return Sting token
     */
    @Override
    public String toString()
    {
        return this.getToken();
    }
}
