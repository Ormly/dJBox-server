package org.pineapple.core;

import java.util.UUID;

/**
 * This class represents a token of the user
 */
public class Token
{
    private UUID token;

    public Token(UUID token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token.toString();
    }

    public void setToken(String token)
    {
        this.token = UUID.fromString(token);
    }
}
