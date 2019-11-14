package org.pineapple.core;

public class AuthenticationFailedException extends RuntimeException
{
    /**
     * This exception can be used to indicate a failed user authentication attempt.
     * @param reason
     */
    public AuthenticationFailedException(String reason)
    {
        super(reason);
    }
}
