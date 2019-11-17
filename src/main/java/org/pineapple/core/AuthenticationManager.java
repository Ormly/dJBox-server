package org.pineapple.core;

import org.pineapple.db.UserDAO;
import org.pineapple.db.interfaces.DAO;
import org.pineapple.utils.interfaces.IAuthenticationManager;

public class AuthenticationManager implements IAuthenticationManager
{
    /**
     * This property provides this class with access to the persistence layer.
     */
    private UserDAO persistenceManager;

    public AuthenticationManager()
    {
        this.persistenceManager = new UserDAO();
    }

    /**
     * Authenticates a given userName and password pair with an existing user in the system.
     * @param userName
     * @param password
     * @return A token to be included in the following requests.
     * @throws AuthenticationFailedException
     */
    @Override
    public String authenticate(String userName, String password)
    throws AuthenticationFailedException
    {
        // TODO: get user by user name from persistence, create hash from given password, compare
        User u = new User("ioncicala", "ksinggrorh44fm45o6l6dova");
        if(userName.equals(u.getUserName()) && doHash256(password).equals(u.getPasswordHash()))
        {
            // User is successfully authenticated
            // 1. generate token
            // 2. update token in User object and save to persistence
            // 3. return token
            return "a1b2c3d4e5";
        }

        // Authentication failed
        throw new AuthenticationFailedException("Authentication failed for user: " + userName);
    }

    /**
     * Handles user logOut event by invalidating the user's token.
     * @param userName
     * @return
     */
    @Override
    public boolean logOut(String userName)
    {
        // TODO: get User from persistence, invalidate its token (delete it??), save back to persistence
        return false;
    }

    /**
     * Creates a new user and saves it to persistence.
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean createUser(String userName, String password)
    {
        return false;
    }

    /**
     * Calculates the hash of a given password, in order to later compare it with the saved hash.
     * (because we don't save passwords!!!).
     * @param s
     * @return
     */
    public static String doHash256(String s)
    {
        // TODO: do this for real
        return "ksinggrorh44fm45o6l6dova";
    }
}
