package org.pineapple.core;

/**
 * Defines the data structure required to preform user authentication.
 */
public class AuthRequest
{
    private String userEmail;
    private String password;

    /**
     * Generates a new authentication request.
     * @param userEmail
     * @param password
     */
    public AuthRequest(String userEmail, String password)
    {
        this.userEmail = userEmail;
        this.password = password;
    }

    /**
     * Returns the user email.
     * @return String userEmail
     */
    public String getUserEmail()
    {
        return userEmail;
    }

    /**
     * Returns the hash256 value of the user password.
     * @return String password.
     */
    public String getPassword()
    {
        return password;
    }
}
