package org.pineapple.core;

import org.pineapple.db.TokenDAO;
import org.pineapple.db.UserDAO;
import org.pineapple.utils.interfaces.IAuthenticationManager;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;
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
        Optional<User> u = this.persistenceManager.get(userName);
        //check if user exists in DB
        if(u.isPresent())
        {
            User user = u.get();

            //if userName and passHash are equal, generate a token
            if(userName.equals(user.getUserName()) && getHash256(password).equals(user.getPasswordHash()))
            {
                /*note the token will be stored in the token table and is only
                  connected with the user in the user class*/
                //generate token
                Token token = new Token(UUID.randomUUID());
                //save token in user class
                user.setToken(token.toString());
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
        Optional<User> u = this.persistenceManager.get(userName);
        //check if user exists in DB
        if(u.isPresent())
        {
            User user = u.get();
            //search for the token in the DB and clear it
            persistenceTokenManager.delete(new Token(UUID.fromString(user.getToken())));
            //clear the token in the user class
            user.setToken("");
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

        Optional<User> u = this.persistenceManager.get(userName);
        //if user already exists
        if(u.isPresent())
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
