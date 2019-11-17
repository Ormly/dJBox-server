/*
This class implements access to database containing User information
 */
package org.pineapple.db;

import org.pineapple.core.User;
import org.pineapple.db.interfaces.DAO;

import java.util.List;
import java.util.Optional;

/**
 * A MySQl implementation for the User table of the DAO interface. Used to perform queries on the Users table.
 */
public class UserDAO implements DAO<User>
{
    @Override
    public Optional<User> get(long id)
    {
        return Optional.empty();
    }

    public Optional<User> get(String id)
    {
        return Optional.empty();
    }

    @Override
    public List<User> getAll()
    {
        return null;
    }

    @Override
    public void save(User user)
    {

    }

    @Override
    public void update(User user, String[] params)
    {

    }

    @Override
    public void delete(User user)
    {

    }
}
