package org.pineapple.utils.interfaces;

import org.pineapple.core.AuthenticationFailedException;

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
     * @param userName
     * @return
     */
    public boolean logOut(String userName);

    /**
     * Creates a new user in the system.
     * @param userName
     * @param password
     * @return
     */
    public boolean createUser(String userName, String password);
}
