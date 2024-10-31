package org.ComicReaderSander.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComicCBZRepo {

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ComicCBZ (\n"
                + "    comicID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    name text UNIQUE NOT NULL,\n"
                + "    numberOfPages integer,\n"
                + "    displayedPage integer,\n"
                + "    filePath text,\n"
                + "    fileType text\n"
                + ");";

        try (Connection connection = SQLite.connect();
             Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertComic(ComicCBZ comic) {
        String sql = "INSERT OR IGNORE INTO ComicCBZ(name, numberOfPages, displayedPage, filePath, fileType) VALUES("
                + "'" + comic.getName() + "', "
                + comic.getNumberOfPages() + ", "
                + comic.getDisplayedPage() + ", '"
                + comic.getFilePath() + "', '"
                + comic.getFileType() + "');";

        try (Connection connection = SQLite.connect();
             Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Comic '" + comic.getName() + "' has been added to the database.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Comic> getAllComics() {
        String sql = "SELECT * FROM ComicCBZ;";
        List<Comic> comicList = new ArrayList<Comic>();

        try (Connection connection = SQLite.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ComicCBZ comicToAdd = new ComicCBZ(
                        rs.getString("name"),
                        rs.getInt("numberOfPages"),
                        rs.getInt("displayedPage"),
                        rs.getString("filePath")
                );

                comicList.add(comicToAdd);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return comicList;
    }

    public static void deleteAllComics() {
        String sql = "DELETE FROM ComicCBZ;";
        try (Connection connection = SQLite.connect();
             Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteComic(Comic comic) {
        String sql = "DELETE FROM ComicCBZ WHERE name = '" + comic.getName() + "';";
        try (Connection connection = SQLite.connect();
             Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
