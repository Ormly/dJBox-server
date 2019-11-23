package org.pineapple.core;

import org.pineapple.db.TokenDAO;
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
    private TokenDAO persistenceTokenManager;

    public AuthenticationManager()
    {
        this.persistenceManager = new UserDAO();
        this.persistenceTokenManager = new TokenDAO();
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
                /*note the token will be stored in the token table and is only
                  connected with the user in the user class*/
                //generate token
                Token token = new Token(UUID.randomUUID());
                //save token in user class
                u.setToken(token.toString());
                //store token in DB
                persistenceTokenManager.save(token);
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
        //check if user exists in DB
        if(persistenceManager.get(userName).isPresent())
        {
            User u = persistenceManager.get(userName).get();
            //search for the token in the DB and clear it
            persistenceTokenManager.delete(new Token(UUID.fromString(u.getToken())));
            //clear the token in the user class
            u.setToken("");
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
        User user = new User(userName, password);

        //if user already exists
        if(persistenceManager.get(userName).isPresent())
            return false;

        //save the new user
        persistenceManager.save(user);
        return true;
    }

    /**
     * Calculates the hash of a given password, in order to later compare it with the saved hash.
     * (because we don't save passwords!!!).
     * @param s
     * @return
     */
    public static String getHash256(String s)
    {
        byte[] hash = null;

        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));

        } catch(Exception e)
        {
        }

        return DatatypeConverter.printHexBinary(hash).toString().toLowerCase();
    }
}
