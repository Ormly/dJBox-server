/*
This class implements access to database containing User information
 */
package org.pineapple.db;

import org.pineapple.core.User;
import org.pineapple.db.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<User> get(long id)
    {
        User u = null;

        try
        {
            openConnection();

            PreparedStatement ps = this.c.prepareStatement("SELECT u.user_id, u.email, u.password\n" +
                                                           "FROM user u\n" +
                                                           "WHERE u.user_id=?;");

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                u = new User(rs.getString("email"),
                             rs.getString("password"),
                             rs.getInt("user_id"));
            }

            closeConnection();

        } catch(SQLException e) {

        }
       return Optional.ofNullable(u);

    }

    public Optional<User> get(String email)
    {
        User u = null;

        openConnection();

       try
        {

            PreparedStatement ps = this.c.prepareStatement("SELECT u.user_id, u.email, u.password\n" +
                                                           "FROM user u\n" +
                                                           "WHERE u.email=?;");

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                u = new User(rs.getString("email"),
                             rs.getString("password"),
                             rs.getInt("user_id"));
            }

            closeConnection();

        } catch(SQLException e)
       {

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
                    "SELECT u.user_id, u.email, u.password\n" +
                    "FROM user u;");

            while (rs.next())
            {
                User u = new User(rs.getString("email"),
                                  rs.getString("password"),
                                  rs.getInt("user_id"));
                userList.add(u);
            }

            closeConnection();

        } catch(SQLException e)
        {
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
                    "INSERT INTO user (user_id, email, role_id, password)" +
                    "VALUES (?,?,2,?)");
            ps.setInt(1, user.getUserID());
            ps.setString(2, user.getUserName());
            ps.setString(4, user.getPasswordHash());

            ps.execute();

            closeConnection();

        } catch(SQLException e)
        {

        }
    }

    @Override
    public void update(User user, String[] params)
    {
        openConnection();

        try
        {
            PreparedStatement ps = this.c.prepareStatement(
                    "INSERT INTO user (user_id, email, role_id, password)" +
                    "VALUES (?,?,2,?)");
            ps.setInt(1, user.getUserID());
            ps.setString(2, user.getUserName());
            ps.setString(4, user.getPasswordHash());

            ps.executeUpdate();

            closeConnection();

        } catch(SQLException e)
        {

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
                    "WHERE user_id = ?\n" +
                    "AND email = ?;");

            ps.setInt(1, user.getUserID());
            ps.setString(2, user.getUserName());

            ps.execute();

            closeConnection();

        } catch(SQLException e)
        {

        }
    }
}