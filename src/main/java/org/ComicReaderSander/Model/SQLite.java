package org.ComicReaderSander.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
    public static Connection connect() {
        Connection connection = null;
        String path = "jdbc:sqlite:src/main/resources/comic.db";
        try {
            connection = DriverManager.getConnection(path);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
