package org.pineapple.core;

/**
 * Defines the data structure required to preform user authentication.
 */
public class AuthRequest
{
    private String userEmail;
    private String password;

    public AuthRequest(String userEmail, String password)
    {
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public String getPassword()
    {
        return password;
    }
}
