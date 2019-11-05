/*
This class implements access to database containing User information
 */
package org.pineapple.db;

import org.pineapple.core.User;
import org.pineapple.db.interfaces.DAO;

import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User>
{
    @Override
    public Optional<User> get(long id)
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
