/*
This class implements access to database containing User information
 */
package org.pineapple.db;

import org.pineapple.core.User;
import org.pineapple.db.interfaces.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A MySQl implementation for the User table of the DAO interface. Used to perform queries on the Users table.
 */
public class UserDAO implements DAO<User>
{
    private Connection c;

    @Override
    public Optional<User> get(long id)
    {
        User u = null;

       /* try
        {
            if(this.c == null || this.c.isClosed())
                c = DBConnection.getConnection();

            Statement s = this.c.createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT u.email, u.firstname, u.lastname, u.password, u.sessionkey\n" +
                         "FROM users u\n" +
                         "WHERE u.sessionkey=" + (int) id + ";");

        } catch(SQLException e) {

        } */
       return Optional.ofNullable(u);

    }

    public Optional<User> get(String id)
    {
        User u = null;

       try
        {
            if(this.c == null || this.c.isClosed())
                c = DBConnection.getConnection();

            Statement s = this.c.createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT u.email, u.password, u.sessionkey\n" +
                         "FROM users u\n" +
                         "WHERE u.sessionkey=" + id + ";");

            if(rs.next()) {
                u = new User(rs.getString("u.email"),
                             rs.getString("u.password"));
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
                    "SELECT u.email, u.password, u.sessionkey\n" +
                    "FROM users u;");

            while (rs.next())
            {
                User u = new User(rs.getString("u.email"),
                                  rs.getString("u.password"));

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
