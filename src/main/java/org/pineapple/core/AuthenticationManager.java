package org.pineapple.core;

import org.pineapple.db.UserDAO;
import org.pineapple.utils.interfaces.IAuthenticationManager;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

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
        //check if user exists in DB
        if(persistenceManager.get(userName).isPresent())
        {
            User u = persistenceManager.get(userName).get();

            //if userName and passHash are equal, generate a token
            if(userName.equals(u.getUserName()) && getHash256(password).equals(u.getPasswordHash()))
            {
                UUID token = UUID.randomUUID();
                u.setToken(token.toString());
                persistenceManager.save(u);

                //return token
                return token.toString();
            }
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
        //check if user exists in DB
        if(persistenceManager.get(userName).isPresent())
        {
            User u = persistenceManager.get(userName).get();
            u.setToken("");
            persistenceManager.save(u);

            return true;
        }

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
    public static String getHash256(String s)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hash).toString().toLowerCase();

        } catch(Exception e) {

            throw new RuntimeException("Error authenticating", e);

        }
    }
}
