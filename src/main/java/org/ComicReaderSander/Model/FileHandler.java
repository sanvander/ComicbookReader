package org.ComicReaderSander.Model;
import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileHandler {
    List<String> acceptedFileTypes = List.of("nhlcomic", "CBR", "CBZ");



    public static void importFile() {
        // make a filechooser with selfmade function
        JFileChooser fileChooser = createFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            File mapForAddedComic = createMapForComic(selectedFile.getName());
            //addComicToMap(mapForAddedComic, selectedFile);

        } else {
            System.out.println("No files selected");

        }
    }

    // Create a map for the comic, also returns the map to be able to add the comic to the map6
    public static File createMapForComic(String name) {
        // format name with selfmade function
        String formattedName = formatFileName(name);

        File comicDir = new File(String.format("src/main/resources/Comics/%s", formattedName));
        comicDir.mkdir();


        return comicDir;
    }


    // Add the comic to the map
    public static void addComicToMap(File comicDir, File comicFile) {
        try {
        Files.copy(comicFile.toPath(), comicDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the file extension from a filename.
     *
     * This method finds the last . in the given filename and removes everything after it,
     * including the . itself. If no . is present, it returns the original filename.
     * This is to be able to create a directory with the name of the comic without the extension.
     *
     * @param name The original filename, including extension.
     * @return The filename without the extension.
     */
    public static String formatFileName(String name) {
        String strippedName;
        // get the index of the last . in the file name
        int dotIndex = name.lastIndexOf(".");
        // remove the extension from the name
        // if for some reason no . is in the filename (shouldn't happen i think) but is possible when debugging it gets skipped
        if (dotIndex != -1) {
            strippedName = name.substring(0, dotIndex);
        } else {
            strippedName = name;
        }
        return strippedName;
    }


    /**
     * Creates and configures a JFileChooser with a specific file filter.
     *
     * This method sets up a JFileChooser that only displays files of specified filetypes:
     * .CBZ, .CBR, and .nhlcomic.
     *
     * @return A JFileChooser instance that displays only files of specified filetypes.
     */
    public static JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        // Create a filter to only show files of the specified filetypes
        // After that add the filter to the filechooser
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(null, "CBZ", "CBR", "nhlcomic");
        fileChooser.setFileFilter(imageFilter);
        return fileChooser;
    }

}
