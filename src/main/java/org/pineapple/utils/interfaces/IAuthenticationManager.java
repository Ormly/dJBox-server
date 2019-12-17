package org.pineapple.utils.interfaces;

import org.pineapple.core.exceptions.AuthenticationFailedException;
import org.pineapple.core.exceptions.RegistrationFailedException;
import org.pineapple.core.exceptions.UserNotAuthenticatedException;

public interface IAuthenticationManager
{
    /**
     * Verifies userName and password are known and matched,
     * and provides a security token to be used in subsequent requests.

     * @param userName
     * @param password
     * @return
     * @throws AuthenticationFailedException
     */

    public String authenticate(String userName, String password)
    throws AuthenticationFailedException;

    /**
     * Invalidates the security token of a logged in user.
     * @param userToken
     * @return
     */
    public boolean logOut(String userToken);

    /**
     * Creates a new user in the database.
     * @param userName
     * @param password
     * @return
     * @throws AuthenticationFailedException
     */
    public void createUser(String userName, String password)
    throws RegistrationFailedException;

    /**
     * Throws an exception if the given does not belong to a logged in user.
     * @param token
     * @throws UserNotAuthenticatedException
     */
    public void validateToke(String token) throws UserNotAuthenticatedException;

}
