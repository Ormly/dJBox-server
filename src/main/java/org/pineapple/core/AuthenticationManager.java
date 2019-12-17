package org.pineapple.core;

import org.pineapple.core.exceptions.AuthenticationFailedException;
import org.pineapple.core.exceptions.RegistrationFailedException;
import org.pineapple.core.exceptions.UserNotAuthenticatedException;
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
        Optional<User> u = this.persistenceManager.get(userName);
        //check if user exists in DB
        if(u.isPresent())
        {
            User user = u.get();

            //if userName and passHash are equal, generate a token
            if(userName.toLowerCase().equals(
                    user.getUserName().toLowerCase())
               && getHash256(password).toLowerCase().equals(
                       user.getPasswordHash().toLowerCase()))
            {

                //generate token
                Token token = new Token(UUID.randomUUID());

                //save token in user class
                user.setToken(token.getToken());

                //store token in DB
                persistenceManager.update(user);

                //return token
                return token.getToken();
            }
        }

        // Authentication failed
        throw new AuthenticationFailedException("Authentication failed for user: " + userName);
    }

    /**
     * Handles user logOut event by invalidating the user's token.
     * @param userToken
     * @return
     */
    @Override
    public boolean logOut(String userToken)
    {
        Optional<User> u = this.persistenceManager.getByToken(userToken);
        //check if user exists in DB
        if(u.isPresent())
        {
            User user = u.get();
            user.setToken(null);
            //search for the token in the DB and clear it
            persistenceManager.update(user);
            return true;
        }

        throw new UserNotAuthenticatedException();
    }

    /**
     * Creates a new user and saves it to persistence.
     * @param userName
     * @param password
     * @return
     */
    @Override
    public void createUser(String userName, String password)
    throws RegistrationFailedException
    {
        User user = new User(userName, password);

        Optional<User> u = this.persistenceManager.get(userName);
        //if user already exists
        if(u.isPresent())
          throw new RegistrationFailedException("Registration failed for user: " + userName);

        //save the new user
        persistenceManager.save(user);
    }

    /**
     * Throws a UserNotAuthenticatedException if the provided token is invalid.
     * @param token
     * @throws UserNotAuthenticatedException
     */
    @Override
    public void validateToke(String token)
    throws UserNotAuthenticatedException
    {
        Optional<User> o = this.persistenceManager.getByToken(token);

        // no user was found with the given token
        if(o.isEmpty())
            throw new UserNotAuthenticatedException();

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

        return DatatypeConverter.printHexBinary(hash).toLowerCase();
    }
}
