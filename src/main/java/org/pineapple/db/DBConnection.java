package org.pineapple.db;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    public enum Database
    {
        MEDIA, AUTHENTICATION
    }

    private static final String PASS = "1234";

    /**
     * Get a connection to database.
     *
     * @return Connection object
     */
    public static Connection getConnection(Database database)

    {
        String URL = "";
        String USER = "";

        try
        {

            switch(database)
            {
                case MEDIA:
                    URL = "jdbc:mysql://localhost:3306/djbox_song_library";
                    USER = "djbox_media";
                    break;
                case AUTHENTICATION:
                    URL = "jdbc:mysql://localhost:3306/djbox_authentication";
                    USER = "djbox_authentication";
                    break;
            }

            DriverManager.registerDriver(new Driver());

            return DriverManager.getConnection(URL, USER, PASS);

        } catch(SQLException ex)
        {

            throw new RuntimeException("Error connecting to the database", ex);

        }

    }


}
