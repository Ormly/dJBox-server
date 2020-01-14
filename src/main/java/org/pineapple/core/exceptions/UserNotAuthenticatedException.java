package org.pineapple.core.exceptions;

public class UserNotAuthenticatedException extends RuntimeException
{
    /**
     * This exception can be used to indicate a user is not authenticated and can't access the
     * requested resource.
     */
    public UserNotAuthenticatedException(){
        super("The current request is missing a valid token!");
    }
}
