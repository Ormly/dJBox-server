package org.pineapple.core.exceptions;

public class RegistrationFailedException extends RuntimeException
{
    /**
     * This exception can be used to indicate a failed user registration attempt.
     * @param reason
     */
    public RegistrationFailedException(String reason)
    {
        super(reason);
    }
}