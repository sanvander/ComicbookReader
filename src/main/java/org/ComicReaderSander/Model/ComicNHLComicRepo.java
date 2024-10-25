package org.ComicReaderSander.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComicNHLComicRepo {

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ComicNHLComic (\n"
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
        } finally {
            try {
                if (SQLite.connect() != null) {
                    SQLite.connect().close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void insertComic(Comic comic) {
        String sql = "INSERT INTO ComicNHLComic(name, numberOfPages, displayedPage, filePath, fileType) VALUES("
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
        String sql = "SELECT * FROM ComicNHLComic;";
        List<Comic> comicList = new ArrayList<Comic>();

        try (Connection connection = SQLite.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ComicNHLComic comicToAdd = new ComicNHLComic(
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








}
