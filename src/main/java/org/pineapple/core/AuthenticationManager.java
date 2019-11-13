package org.pineapple.core;

import org.pineapple.db.interfaces.DAO;
import org.pineapple.utils.interfaces.IAuthenticationManager;

public class AuthenticationManager implements IAuthenticationManager
{
    private DAO<User> persistenceManager;

    public AuthenticationManager(DAO<User> persistenceManager){
        this.persistenceManager = persistenceManager;
    }

    @Override
    public String authenticate(String userName, String password)
    throws AuthenticationFailedException
    {
        // TODO: get user by user name from persistence, create hash from given password, compare
        User u = new User("ioncicala", "ksinggrorh44fm45o6l6dova");
        if(userName.equals(u.getUserName()) && doHash256(password).equals(u.getPasswordHash())){
            // User is successfully authenticated
            // 1. generate token
            // 2. update token in User object and save to persistence
            // 3. return token
            return "a1b2c3d4e5";
        }

        // Authentication failed
        throw new AuthenticationFailedException("Authentication failed for user: " + userName);
    }

    @Override
    public boolean logOut(String userName)
    {
        // TODO: get User from persistence, invalidate its token (delete it??), save back to persistence
        return false;
    }

    @Override
    public boolean createUser(String userName, String password)
    {
        return false;
    }

    public static String doHash256(String s){
        // TODO: do this for real
        return "ksinggrorh44fm45o6l6dova";
    }
}
