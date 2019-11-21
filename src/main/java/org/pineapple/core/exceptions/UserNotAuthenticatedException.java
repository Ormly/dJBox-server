package org.pineapple.core.exceptions;

public class UserNotAuthenticatedException extends RuntimeException
{
    public UserNotAuthenticatedException(){
        super("The current request is missing a valid token!");
    }
}
