package org.pineapple.core;

/**
 * Defines the data structure given back to client in response for a successful authentication process.
 */
public class AuthResponse
{
    private String token;

    public AuthResponse(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }
}
