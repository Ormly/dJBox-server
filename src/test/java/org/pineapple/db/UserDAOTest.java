package org.pineapple.db;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.pineapple.core.User;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAOTest
{
    static private UserDAO u;
    static private Connection c;
    static private User usr;
    static private String token;
    static List<String> cleanUp = new ArrayList<>();

    @BeforeAll
    static void beforeAll()
    {
        u = new UserDAO();
        c = DBConnection.getConnection(DBConnection.Database.AUTHENTICATION);
    }

    @Test
    void saveNewUser()
    throws SQLException
    {
        u.save(new User("test1234@yahoo.com",
                        "b9c950640e1b3740e98acb93e669c65766f6670dd1609ba91ff41052ba48c6f3"));

        Statement s1 = c.createStatement();

        ResultSet rs1 = s1.executeQuery("SELECT email FROM user WHERE email='test1234@yahoo.com'");

        cleanUp.add("test1234@yahoo.com");

        Assertions.assertTrue(rs1.next());
    }

    @Test
    void getUserByEmail()
    throws SQLException
    {
        if(u.get("testperson@gmail.com").isPresent())
             usr = u.get("testperson@gmail.com").get();

        Assertions.assertEquals("testperson@gmail.com", usr.getUserName());
    }

    @Test
    void updateUserToken()
    throws SQLException
    {
        if(u.get("testperson@gmail.com").isPresent())
        {
            usr = u.get("testperson@gmail.com").get();

            usr.setToken(UUID.randomUUID().toString());
            token = usr.getToken();

            u.update(usr);

            usr = u.get("testperson@gmail.com").get();
        }

        Assertions.assertEquals(usr.getToken(), token);
    }

    @Test
    void getUserByToken()
    throws SQLException
    {
        User temp = new User("test12345@yahoo.com",
                             "b9c950640e1b3740e98acb93e669c65766f6670dd1609ba91ff41052ba48c6f3");

        u.save(temp);

        temp.setToken(UUID.randomUUID().toString());
        token = temp.getToken();

        u.update(temp);

        if(u.getByToken(token).isPresent())
            usr = u.getByToken(token).get();

        cleanUp.add("test12345@yahoo.com");

        Assertions.assertEquals("test12345@yahoo.com", usr.getUserName());
    }

    @Test
    void removeUserFromPersistenceTest()
    throws SQLException
    {
        System.out.println(cleanUp);
        for(String s : cleanUp)
        {
            if(u.get(s).isPresent())
                usr = u.get(s).get();

            u.delete(usr);
        }

        ResultSet rs;

        for(String s : cleanUp)
        {
            PreparedStatement ps = this.c.prepareStatement(
                    "SELECT email FROM user WHERE email=?;");

            ps.setString(1, s);
            rs = ps.executeQuery();

            Assertions.assertFalse(rs.next());
        }

        cleanUp = null;

    }
}
