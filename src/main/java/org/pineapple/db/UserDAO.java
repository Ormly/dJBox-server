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
    //TODO: update when Tim generates a new connection class
    private Connection c;

    @Override
    public Optional<User> get(long id)
    {
        User u = null;

        try
        {
            if(this.c == null || this.c.isClosed())
                c = DBConnection.getConnection();

            PreparedStatement ps = this.c.prepareStatement("SELECT u.user_id, u.email, u.password, u.sessionkey\n" +
                                                           "FROM user u\n" +
                                                           "WHERE u.user_id=?;");

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                u = new User(rs.getString("email"),
                             rs.getString("password"),
                             rs.getLong("user_id"));
                //u.setToken(rs.getString("sessionkey"));
            }

            this.c.close();

        } catch(SQLException e) {

        }
       return Optional.ofNullable(u);

    }

    //TODO: whitelisting? || limit input chars
    public Optional<User> get(String email)
    {
        User u = null;

       try
        {
            if(this.c == null || this.c.isClosed())
                c = DBConnection.getConnection();

            PreparedStatement ps = this.c.prepareStatement("SELECT u.user_id, u.email, u.password, u.sessionkey\n" +
                                                           "FROM user u\n" +
                                                           "WHERE u.email=?;");

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                u = new User(rs.getString("email"),
                             rs.getString("password"),
                             rs.getLong("user_id"));
                //u.setToken(rs.getString("sessionkey"));
            }

            this.c.close();

        } catch(SQLException e)
       {

       }

       return Optional.ofNullable(u);
    }

    @Override
    public List<User> getAll()
    {
        List<User> userList = new ArrayList<>();

        try
        {
            if(this.c == null || this.c.isClosed())
                c = DBConnection.getConnection();

            Statement s = this.c.createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT u.user_id, u.email, u.password, u.sessionkey\n" +
                    "FROM user u;");

            while (rs.next())
            {
                User u = new User(rs.getString("email"),
                                  rs.getString("password"),
                                  rs.getLong("user_id"));
                //u.setToken(rs.getString("sessionkey"));

                userList.add(u);
            }

            this.c.close();

        } catch(SQLException e) {

        }

        return userList;
    }

    @Override
    public void save(User user)
    {

        try
        {
            if(this.c == null || this.c.isClosed())
                c = DBConnection.getConnection();

            PreparedStatement ps = this.c.prepareStatement(
                    "INSERT INTO user (user_id, email, role_id, password, sessionkey)" +
                    "VALUES (?,?,2,?,?)");
            ps.setString(1, user.getUserName());
            //ps.setLong(2, user.);

        } catch(SQLException e)
        {

        }
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
