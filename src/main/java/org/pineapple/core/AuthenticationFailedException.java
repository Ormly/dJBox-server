package org.pineapple.core;

public class AuthenticationFailedException extends RuntimeException
{
    public AuthenticationFailedException(String reason)
    {
        super(reason);
    }
}
