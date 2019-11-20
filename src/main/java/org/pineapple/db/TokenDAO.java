package org.pineapple.db;
/**
 * This class implements access to database containing Token information
 */

import org.pineapple.core.Token;
import org.pineapple.db.interfaces.DAO;
import java.util.List;
import java.util.Optional;

//TODO: fill out the TokenDAO class
public class TokenDAO implements DAO<Token>
{

    @Override
    public Optional<Token> get(long id)
    {
        return Optional.empty();
    }

    @Override
    public List<Token> getAll()
    {
        return null;
    }

    @Override
    public void save(Token token)
    {

    }

    @Override
    public void update(Token token, String[] params)
    {

    }

    @Override
    public void delete(Token token)
    {

    }
}
