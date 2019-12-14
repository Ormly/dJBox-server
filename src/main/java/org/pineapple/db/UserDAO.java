/*
This class implements access to database containing User information
 */
package org.pineapple.db;

import jdk.jshell.spi.ExecutionControl;
import org.pineapple.core.Token;
import org.pineapple.core.User;
import org.pineapple.db.interfaces.DAO;

import javax.swing.text.html.Option;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * A MySQl implementation for the User table of the DAO interface. Used to perform queries on the Users table.
 */
public class UserDAO implements DAO<User>
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }


    @Override
    public Optional<User> get(long id)
    {
        return Optional.empty();
    }

    public Optional<User> getByToken(String token){
        User u = null;

        openConnection();

        try
        {

            PreparedStatement ps = this.c.prepareStatement("SELECT u.email, u.password, u.token\n" +
                                                           "FROM user u\n" +
                                                           "WHERE u.token=?;");

            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                u = new User(rs.getString("email"),
                             rs.getString("password"));

                u.setToken(token);
            }

            closeConnection();

        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return Optional.ofNullable(u);
    }

    public Optional<User> get(String email)
    {
        User u = null;

        openConnection();

       try
        {

            PreparedStatement ps = this.c.prepareStatement("SELECT u.email, u.password, u.token\n" +
                                                           "FROM user u\n" +
                                                           "WHERE u.email=?;");

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                u = new User(rs.getString("email"),
                             rs.getString("password"));


                String strToken = rs.getString("token");

                // if user already has a token, set it. default is null.
                if (strToken != null){
                    Token t = new Token(UUID.fromString(strToken));
                    u.setToken(t.toString());
                }
            }

            closeConnection();

        } catch(SQLException e)
       {
           e.printStackTrace();
       }

       return Optional.ofNullable(u);
    }

    @Override
    public List<User> getAll()
    {
        List<User> userList = new ArrayList<>();

        openConnection();

        try
        {
            Statement s = this.c.createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT u.email, u.password, u.token\n" +
                    "FROM user u;");

            while (rs.next())
            {
                User u = new User(rs.getString("email"),
                                  rs.getString("password"));

                String strToken = rs.getString("token");
                if(strToken != null){
                    Token t = new Token(UUID.fromString(strToken));
                    u.setToken(t.getToken());
                }

                userList.add(u);
            }

            closeConnection();

        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void save(User user)
    {

        openConnection();

        try
        {
            PreparedStatement ps = this.c.prepareStatement(
                    "INSERT INTO user (email, role_id, password)" +
                    "VALUES (?,2,?);");
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPasswordHash());

            ps.execute();

            closeConnection();

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user)
    {
        openConnection();

        try
        {
            PreparedStatement ps = this.c.prepareStatement(
                    "UPDATE user SET role_id=?, password=?, token=? " +
                         "WHERE email=?");
            ps.setInt(1,2);
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getToken());
            ps.setString(4, user.getUserName());

            ps.executeUpdate();

            closeConnection();

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user)
    {
        openConnection();

        try
        {
            PreparedStatement ps = this.c.prepareStatement(
                    "DELETE FROM user\n" +
                    "WHERE email = ?;");

            ps.setString(1, user.getUserName());

            ps.execute();

            closeConnection();

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}