package org.pineapple.db;
/**
 * This class implements access to database containing Token information
 */

import org.pineapple.core.Token;
import org.pineapple.db.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//TODO: fill out the TokenDAO class
public class TokenDAO implements DAO<Token>
{
    private Connection c;

    private void openConnection()
    {
        try
        {
            if(this.c == null || this.c.isClosed())
                this.c = DBConnection.getConnection(DBConnection.Database.AUTHENTICATION);
        } catch(SQLException e)
        {

        }
    }

    private void closeConnection()
    {
        try
        {
            if(this.c != null)
                this.c.close();
        } catch(SQLException e)
        {

        }
    }

    @Override
    public Optional<Token> get(long id)
    {
        Token t = null;

        openConnection();
        //TODO: get token from long?

        return Optional.empty();
    }

    @Override
    public List<Token> getAll()
    {
        List<Token> tokenList = new ArrayList<>();

        openConnection();

        try
        {
            Statement s = this.c.createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT token FROM token;");

            while(rs.next())
            {
                Token t = new Token(UUID.fromString(rs.getString("token")));
                tokenList.add(t);
            }
        } catch(SQLException e)
        {
        }

        return tokenList;
    }

    @Override
    public void save(Token token)
    {
        openConnection();

        try
        {
            PreparedStatement ps = this.c.prepareStatement(
                    "INSERT INTO token VALUES (?);");
            ps.setString(1, token.getToken());

            ps.execute();

            closeConnection();

        } catch(SQLException e)
        {

        }
    }

    @Override
    public void update(Token token, String[] params)
    {
        openConnection();

        try
        {
            PreparedStatement ps = this.c.prepareStatement(
                    "INSERT INTO token VALUES (?);");
            ps.setString(1, token.getToken());

            ps.executeUpdate();

            closeConnection();

        } catch(SQLException e)
        {

        }
    }

    @Override
    public void delete(Token token)
    {
        openConnection();

        try
        {
            PreparedStatement ps = this.c.prepareStatement(
                    "DELETE FORM token WHERE token = ?;");

            ps.setString(1, token.getToken());

            ps.execute();

            closeConnection();

        } catch(SQLException e)
        {

        }

    }
}
